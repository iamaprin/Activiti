package me.vilya.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

public class StudentLeaveProcess {
	
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	public Deployment deploy() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("diagrams/student_leave.bpmn")
				.name("学生请假流程")
				.deploy();
		return deployment;
	}
	
	public ProcessInstance start(String processDefinitionId) {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
		return processInstance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
