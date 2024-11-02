package com.example.bank_account_service.repositories;

import com.example.bank_account_service.entities.BankAccount;
import com.example.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RepositoryRestResource//demarer un web service resfull  qui permet gener une entitie de type BankAccout automatiquement il est cree put get post delet et en
//spring.data.rest.base-path=/api



public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    @RestResource(path="/byType")
    List<BankAccount> findByType(@Param("t") AccountType type); //EXEMPLE http://localhost:8081/bankAccounts/search/findByType?type=SAVING_ACCOUNT

    //List<BankAccount> findByType( AccountType type);// exemple  http://localhost:8081/bankAccounts/search/byType?t=SAVING_ACCOUNT
}
