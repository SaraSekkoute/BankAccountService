package com.example.bank_account_service.web;

import com.example.bank_account_service.dto.BankAccountRequestDTO;
import com.example.bank_account_service.dto.BankAccountResponseDTO;
import com.example.bank_account_service.entities.BankAccount;
import com.example.bank_account_service.mappers.AccountMapper;
import com.example.bank_account_service.repositories.BankAccountRepository;
import com.example.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class AccountRestController {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/BankAccounts/")
    public List<BankAccount> BankAccounts()
    {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/BankAccounts/{id}")
    public BankAccount  BankAccounts(@PathVariable String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccount")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO)
    {
        return accountService.addAccount(requestDTO);
    }
   /* public  BankAccount save(@RequestBody BankAccount BankAccount)
    {
        if(BankAccount.getId() == null) BankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(BankAccount);
    }*

    */

    @PutMapping("/bankAccounts/{id}")
    public  BankAccount update(@PathVariable String id,@RequestBody BankAccount BankAccount)
    {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
        //pour n'estpas modifier tous les attribues
        if (BankAccount.getBalance()!= null)account.setBalance(BankAccount.getBalance());
        if (BankAccount.getCreateAt()!= null)account.setCreateAt(new Date());
        if (BankAccount.getCreateAt()!= null)account.setType(BankAccount.getType());
        if (BankAccount.getCurrency()!= null)account.setCurrency(BankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id)
    {

         bankAccountRepository.deleteById(id);
    }
}
