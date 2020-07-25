package com.kalusyu.patterns;

import org.junit.jupiter.api.Test;

public class ObjectUnitTest {

    @Test
    public void testObject(){
        AI ai = new AI();
    }
}

class AI{
    public AI(){
        DataObject object = DataObject.INSTANCE;
        object.testData();
    }
}
