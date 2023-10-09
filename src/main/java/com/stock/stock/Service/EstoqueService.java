package com.stock.stock.Service;

import com.stock.stock.dto.EstoqueContentDTO;
import com.stock.stock.dto.EstoqueDTO;
import com.stock.stock.entity.*;
import com.stock.stock.repository.*;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Value("${application.APP_ID}")
    private String APP_ID;

    @Value("${application.YOUR_URL}")
    private String YOUR_URL;

    @Value("${application.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Autowired
    private SkuSimplesRepository skuSimplesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private EstoqueContentRepository estoqueContentRepository;


    public Estoque create(EstoqueDTO estoqueDTO, User user){

            Estoque novo_estoque = new Estoque();
            BeanUtils.copyProperties(estoqueDTO, novo_estoque);
            novo_estoque.setUser(user);
            Conta novaConta = contaRepository.findById(estoqueDTO.getConta()).get();
            novo_estoque.setConta(novaConta);
            repository.save(novo_estoque);
            return novo_estoque;


    }

    public Estoque findById(Integer id, User user) {


            Optional<Estoque> optionalEstoque = repository.findById(id);
            if( optionalEstoque.get().getUser() == user) {
                return optionalEstoque.get();
            } else {
                throw new RuntimeException("Voce nao tem acesso a esse estoque");
            }



    }

    public List<Estoque> getAll( User user){

        return repository.findAllByUser(user);

    }

    public Estoque update(Estoque estoque, EstoqueDTO estoqueDTO){


            BeanUtils.copyProperties(estoqueDTO,estoque);
            repository.save(estoque);
            return estoque;

    }


    /////////ESTOQUE CONTENT ///////

    public EstoqueContent addContent(EstoqueContentDTO estoqueContentDTO, User user) {

        EstoqueContent estoqueContent = new EstoqueContent();

try {

    estoqueContent.setEstoque(repository.findById(estoqueContentDTO.getEstoque()).get());
    estoqueContent.setSkuSimples(skuSimplesRepository.findBySKU(estoqueContentDTO.getSkuSimples()).get());
    estoqueContent.setQuantidade_real(estoqueContentDTO.getQuantidade_real());
    estoqueContent.setQantidade_deduzida(estoqueContentDTO.getQuantidade_real());
    estoqueContent.setUser(user);
    estoqueContentRepository.save(estoqueContent);
    return  estoqueContent;


} catch (Exception e) {
    throw new RuntimeException("nao foi posivel adicionar item ao estoque");
}


    }

    public List<EstoqueContent> getAllByEstoque( User user, Integer id){

        Optional<Estoque> optionalEstoque = repository.findById(id);
        if (optionalEstoque.get().getUser() == user){

                List<EstoqueContent> estoqueContentList = estoqueContentRepository.findAllByEstoque(optionalEstoque.get());
                return estoqueContentList;


        } else  {
            throw new RuntimeException("Voce nao tem permissao para acessar esse estoque");
        }



    }

    public EstoqueContent updateContent( EstoqueContentDTO estoqueContentDTO, EstoqueContent estoqueContent, User user){

        if (estoqueContent.getUser() == user) {
            BeanUtils.copyProperties(estoqueContentDTO,estoqueContent);
            estoqueContentRepository.save(estoqueContent);
            return  estoqueContent;
        } else {
            throw new RuntimeException("Voce nao tem permissao para alterar esse item");
        }

    }

    public EstoqueContent delete( EstoqueContent estoqueContent, User user){

        if (estoqueContent.getUser() == user) {
            estoqueContentRepository.delete(estoqueContent);
            return  estoqueContent;
        } else {
            throw new RuntimeException("Voce nao tem permissao para deletar esse item");
        }

    }

    public void AtualizaEstoque(Order order){

        User user = order.getConta().getUsuario();
        SkuSimples skuSimples = order.getSku();


        Estoque estoque =   repository.findByConta(order.getConta()).get();


            Optional<EstoqueContent> estoqueContentOptional = estoqueContentRepository.findByUserAndSkuSimplesAndAndEstoque(user,skuSimples,estoque);
            estoqueContentOptional.get().setQantidade_deduzida(estoqueContentOptional.get().getQantidade_deduzida() - order.getQuantidade());
            estoqueContentRepository.save(estoqueContentOptional.get());
            System.out.println("Estoque atualizado");





    }















}
