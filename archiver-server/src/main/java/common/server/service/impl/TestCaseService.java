package common.server.service.impl;

import common.server.domain.TestCase;
import common.server.exception.NotFoundException;
import common.server.repository.TestCaseRepository;
import common.server.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService implements ITestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public TestCase addTestCase(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }

    @Override
    public TestCase findTestCase(long id) throws NotFoundException {
        return testCaseRepository.findById(id).get();
    }

    @Override
    public List<TestCase> findAllTestCases() {
        return testCaseRepository.findAll();
    }

    @Override
    public void deleteTestCase(long id) throws NotFoundException {
        testCaseRepository.deleteById(id);
    }

    @Override
    public List<TestCase> searchTestCases(TestCase testCase) throws NotFoundException {
        return null;
    }

    @Override
    public TestCase findTestCaseByUsername(String username) throws NotFoundException {
        return null;
    }

    @Override
    public List<TestCase> getConnectedUserTestCases() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }
}
