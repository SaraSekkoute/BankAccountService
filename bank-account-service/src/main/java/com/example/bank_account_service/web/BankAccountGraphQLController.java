package com.example.bank_account_service.web;

import com.example.bank_account_service.dto.BankAccountRequestDTO;
import com.example.bank_account_service.dto.BankAccountResponseDTO;
import com.example.bank_account_service.entities.BankAccount;
import com.example.bank_account_service.entities.Customer;
import com.example.bank_account_service.repositories.BankAccountRepository;
import com.example.bank_account_service.repositories.CustomerRepository;
import com.example.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class BankAccountGraphQLController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountList()
    {
        return bankAccountRepository.findAll();
    }


    @QueryMapping
    public BankAccount BankAccountById(@Argument String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }


    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount)
    {
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
public BankAccountResponseDTO updateAccount(@Argument String id ,@Argument BankAccountRequestDTO bankAccount)
{
    return accountService.updateAccount(id,bankAccount);
    }




    @MutationMapping
    public  Boolean deleteAccount(@Argument String id )
    {
      accountService.deleteAccount(id);
      return true;
    }

    @QueryMapping
    public List<Customer> customers()
    {

   return customerRepository.findAll();

    }
}
/*record BankAccountDTO(   Float balance,
                         String currency,
                         String type )
{

}*/

