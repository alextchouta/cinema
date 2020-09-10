package org.sid.bankspringbatch.config;

import lombok.AllArgsConstructor;
import org.sid.bankspringbatch.model.BankTransaction;
import org.sid.bankspringbatch.repository.BankTransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BankITransactionItemWriter implements ItemWriter<BankTransaction> {

    private BankTransactionRepository bankTransactionRepository;

    @Override
    public void write(List<? extends BankTransaction> list) throws Exception {
        bankTransactionRepository.saveAll(list);
    }
}
