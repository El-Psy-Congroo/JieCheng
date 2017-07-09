package com.JieCheng.util;

import com.JieCheng.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Regex Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 25, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RegexTest {
    @Autowired
    Regex regex;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: NumOneToFour(String content)
     */
    @Test
    public void testNumOneToFour() throws Exception {
    }

    /**
     * Method: NumPositiveInt(String content)
     */
    @Test
    public void testNumPositiveInt() throws Exception {
    }


} 
