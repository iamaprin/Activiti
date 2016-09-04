package me.vilya.activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;


public class ActivitiTest {
	
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	public static final ActivitiTest activitiTest = new ActivitiTest();

	public void test1() {
		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine processEngine = pec.buildProcessEngine();
	}
	
	public void test2() {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
	}
	
	public void deploy() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
					.addClasspathResource("diagrams/test.bpmn")
					.name("test")
					.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
	}
	
	public void start() {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
		System.out.println(processInstance.getId());
		
	}
	
	public void findTask() {
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("vilya").list();
		for (Task task : tasks) {
			System.out.println(task.getAssignee());
			System.out.println(task.getId());
			System.out.println(task.getProcessDefinitionId());
			System.out.println(task.getTaskDefinitionKey());
		}
	}
	
	public String getTaskId(String assignee) {
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee("vilya")
				.list();
		return tasks.get(0).getId();
	}
	
	public void complete() {
		TaskService taskService = processEngine.getTaskService();
		taskService.complete(getTaskId("vilya"));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new ActivitiTest().start();
		activitiTest.complete();
	}

}
