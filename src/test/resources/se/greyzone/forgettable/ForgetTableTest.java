package se.greyzone.forgettable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ForgetTableTest {

    ForgetTable<String> forgetTable;

    @Before
    public void setup() {
        forgetTable = new ForgetTable<>(0.02F);
    }

    @Test
    public void testIncrement() throws Exception {
        forgetTable.increment("se");

        Assert.assertEquals(1, forgetTable.getValue("se"));
        Assert.assertEquals(0, forgetTable.getValue("not valid"));

        forgetTable.increment("se");
        forgetTable.increment("not valid");

        Assert.assertEquals(2, forgetTable.getValue("se"));
        Assert.assertEquals(1, forgetTable.getValue("not valid"));
    }

    @Test
    public void testIncrementVariable() throws Exception {
        forgetTable.increment("se", 4);
        Assert.assertEquals(4, forgetTable.getValue("se"));

        forgetTable.increment("se", 2);
        Assert.assertEquals(6, forgetTable.getValue("se"));
    }

    @Test
    public void testDecrement() throws Exception {
        forgetTable.decrement("se", 4);
        Assert.assertEquals(0, forgetTable.getValue("se"));

        forgetTable.increment("se", 2);
        Assert.assertEquals(2, forgetTable.getValue("se"));

        forgetTable.decrement("se");
        Assert.assertEquals(1, forgetTable.getValue("se"));

        forgetTable.decrement("se", 4);
        Assert.assertEquals(0, forgetTable.getValue("se"));
    }

    @Test
    public void testDecrementVariable() throws Exception {

    }
}
