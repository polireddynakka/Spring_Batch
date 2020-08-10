package com.spring.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class SpringBatch2Application {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job(){
		return this.jobBuilderFactory.get("basicJob")
				.start(step9())
				.build();
	}

//Example 1
//	@Bean
//	public Step step4(){
//		return stepBuilderFactory.get("step4")
//								.tasklet(new Tasklet() {
//									@Override
//									public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//										System.out.println("HELLO WORLD BABY !");
//										return RepeatStatus.FINISHED;
//									}
//								}).build();
//	}

////	Example2
//	public Step step9(){
//		return stepBuilderFactory.get("step9")
//							.tasklet(HiWorldTasklet())
//							.build();
//	}
//	@Bean
//	public Tasklet HiWorldTasklet(){
//		return (contribution, chunkContext) -> {
//			String name = (String) chunkContext.getStepContext()
//					.getJobParameters()
//					.get("name");
//			System.out.println(String.format("HELLO, %s!", name));
//			return RepeatStatus.FINISHED;
//		};
//	}
//@Bean
//	public Step step9(){
//		return stepBuilderFactory.get("step9")
//							.tasklet(HiWorldTasklet())
//							.build();
//	}
public Step step9(){
		return stepBuilderFactory.get("step9")
							.tasklet(HiWorldTasklet(null))
							.build();
	}
	@StepScope
	@Bean
	public Tasklet HiWorldTasklet(@Value("#{jobParameters['name']}") String name){
		return (contribution, chunkContext) -> {
			System.out.println(String.format("HELLO, %s!", name));
			return RepeatStatus.FINISHED;
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBatch2Application.class, args);
	}

}
