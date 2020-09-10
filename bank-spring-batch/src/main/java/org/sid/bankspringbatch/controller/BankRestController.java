package org.sid.bankspringbatch.controller;

import lombok.AllArgsConstructor;
import org.sid.bankspringbatch.config.BankTransactionItemAnalyticsProcessor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BankRestController {


    private JobLauncher jobLauncher;

    private BankTransactionItemAnalyticsProcessor itemAnalyticsProcessor;

    private Job job;

    @GetMapping("/startJob")
    public BatchStatus load() throws Exception {

        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("time", new JobParameter(System.currentTimeMillis()));

        JobParameters jobParameters = new JobParameters(parameters);

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        while (jobExecution.isRunning())
        {
            System.out.println("..........");
        }
        return jobExecution.getStatus();
    }

   @GetMapping("/analytics")
    public Map<String,Double> analytics()
    {
        Map<String,Double> map = new HashMap<>();
        map.put("totalCredit", itemAnalyticsProcessor.getTotalCredit());
        map.put("totalDebit", itemAnalyticsProcessor.getTotalDebit());
        return  map;
    }
}
