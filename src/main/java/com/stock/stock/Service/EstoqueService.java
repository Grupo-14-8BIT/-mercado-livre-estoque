package com.stock.stock.Service;

import com.stock.stock.dto.EstoqueContentDTO;
import com.stock.stock.dto.EstoqueDTO;
import com.stock.stock.entity.Estoque;
import com.stock.stock.entity.EstoqueContent;
import com.stock.stock.repository.*;
import com.stock.stock.user.User;
import com.stock.stock.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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


    private Estoque create(EstoqueDTO estoqueDTO){
        try{
            Estoque novo_estoque = new Estoque();
            BeanUtils.copyProperties(estoqueDTO, novo_estoque);
            repository.save(novo_estoque);
            return novo_estoque;
        } catch (Exception e) {
            throw new RuntimeException("nao foi criado com sucesso");
        }

    }

    private Estoque findById(Integer id) {

        try {
            return repository.findById(id).get();
        } catch (Exception e){
            throw new RuntimeException("Nao foi possivel encontar o estoque, Motivo: " + e.getCause().toString());
        }

    }

    private List<Estoque> GetAll( User user){
    try {
        return repository.findAllByUser(user);
    } catch (Exception e) {
        throw  new RuntimeException("Nao foi pssivel retornar nenhum estoque");
    }

    }

    private Estoque update(Estoque estoque, EstoqueDTO estoqueDTO){

        try {
            BeanUtils.copyProperties(estoqueDTO,estoque);
            repository.save(estoque);
            return estoque;
        } catch (Exception e) {
            throw new RuntimeException("Nao foi possivel atualizar o estoque");
        }
    }


    /////////ESTOQUE CONTENT ///////

    private EstoqueContent create (EstoqueContentDTO estoqueContentDTO) {

        EstoqueContent estoqueContent = new EstoqueContent();

        try {
            BeanUtils.copyProperties(estoqueContentDTO, estoqueContent);
            estoqueContentRepository.save(estoqueContent);
            return  estoqueContent;
        } catch (Exception e) {
            throw new RuntimeException("Nao foi possivel entrar em contato");

        }


    }

    private List<EstoqueContent> getAllByEstoque( Estoque estoque){
        try {
            List<EstoqueContent> estoqueContentList = estoqueContentRepository.findAllByEstoque(estoque);
            return estoqueContentList;
        } catch (Exception e) {
            throw new RuntimeException("Nada a retornar");
        }

    }

    private EstoqueContent update( EstoqueContentDTO estoqueContentDTO, EstoqueContent estoqueContent, User user){

        if (estoqueContent.getUser() == user) {
            BeanUtils.copyProperties(estoqueContentDTO,estoqueContent);
            estoqueContentRepository.save(estoqueContent);
            return  estoqueContent;
        } else {
            throw new RuntimeException("Voce nao tem permissao para alterar esse item");
        }

    }

    private EstoqueContent delete( EstoqueContent estoqueContent, User user){

        if (estoqueContent.getUser() == user) {
            estoqueContentRepository.delete(estoqueContent);
            return  estoqueContent;
        } else {
            throw new RuntimeException("Voce nao tem permissao para deletar esse item");
        }

    }

//    private EstoqueContent AtualizaEstoque(Order order){
//
//        Optional<EstoqueContent> estoqueContentOptional = estoqueContentRepository.findBy
//
//
//    }















}
