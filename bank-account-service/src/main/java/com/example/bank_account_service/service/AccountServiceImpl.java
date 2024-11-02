package com.example.bank_account_service.service;

import com.example.bank_account_service.dto.BankAccountRequestDTO;
import com.example.bank_account_service.dto.BankAccountResponseDTO;
import com.example.bank_account_service.entities.BankAccount;
import com.example.bank_account_service.enums.AccountType;
import com.example.bank_account_service.mappers.AccountMapper;
import com.example.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional//tous les methode son,t transactionnel
public class AccountServiceImpl implements AccountService {


    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        //Servcie
        BankAccount bA =BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .type(bankAccountDTO.getType())
                .balance(bankAccountDTO.getBalance())
                .createAt(new Date())
                .currency(bankAccountDTO.getCurrency())
                .build();
        //metier
        BankAccount saveBankAccount =bankAccountRepository.save(bA);
        //transfere les données
        /*BankAccountResponseDTO bankAccountResponseDTO=BankAccountResponseDTO.builder()
                .id(saveBankAccount.getId())
                .type(saveBankAccount.getType())
                .createAt(saveBankAccount.getCreateAt())
                .currency(saveBankAccount.getCurrency())
                .build();*/
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id ,BankAccountRequestDTO bankAccountDTO)
    {
        BankAccount bA =BankAccount.builder()
                .id(id)
                .createAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount =bankAccountRepository.save(bA);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }

    @Override
    public void deleteAccount(String id )
    {
        bankAccountRepository.deleteById(id);
    }
}
