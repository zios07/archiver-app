package common.server.service;

import common.server.domain.TestCase;
import common.server.domain.TreeNode;
import common.server.exception.NotFoundException;

import java.util.List;

public interface ITestCaseService {

	TestCase addTestCase(TestCase testCase);

	TestCase findTestCase(long id) throws NotFoundException;

	List<TestCase> findAllTestCases();

	void deleteTestCase(long id) throws NotFoundException;

	List<TestCase> searchTestCases(TestCase testCase) throws NotFoundException;
	
	TestCase findTestCaseByUsername(String username) throws NotFoundException;

    List<TestCase> getConnectedUserTestCases();

}
