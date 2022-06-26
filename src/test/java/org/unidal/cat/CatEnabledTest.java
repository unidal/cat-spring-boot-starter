package org.unidal.cat;

import com.dianping.cat.Cat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unidal.cat.annotation.EnableCat;
import org.unidal.cat.autoconfigure.CatAutoConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatAutoConfiguration.class)
@EnableCat
public class CatEnabledTest {
   @Test
   public void test() {
      Assert.assertEquals(true,  Cat.getBootstrap().isInitialized());
   }
}
