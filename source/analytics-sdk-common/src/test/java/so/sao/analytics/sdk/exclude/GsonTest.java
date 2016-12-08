package so.sao.analytics.sdk.exclude;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Google JSON library VS Ali FastJson library testing
 *
 * @author senhui.li
 */
public class GsonTest {

    private final int MAX_NUM = 100000;
    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().setVersion(1.0)
            .create();

    @SuppressWarnings("unchecked")
    @Test
    public void testJSONSerialize() throws Exception {

        SerializeObject object = new SerializeObject();
        object.setFullName("Gson");
        object.setAge(2);
        object.setSex(new Integer(0));
        object.setTreasure(new Random().nextDouble());
        object.setChildrens(Arrays.asList("Google", "Json", "Library"));

        for (int k = 0; k < 10; k++) {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < MAX_NUM; i++) {
                gson.toJson(object);
            }
            long t2 = System.currentTimeMillis();
            System.out.println("Gson Serialize " + MAX_NUM + " times spent times: " + (t2 - t1));

            t1 = System.currentTimeMillis();
            for (int i = 0; i < MAX_NUM; i++) {
                JSON.toJSONString(object);
            }
            t2 = System.currentTimeMillis();
            System.out.println("FastJson Serialize " + MAX_NUM + " times spent times: " + (t2 - t1));
        }

        String json = gson.toJson(object);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        System.out.println(map);
        assertNotNull(map);
        assertEquals(map.get("name"), "Gson");
        assertTrue(Double.valueOf(map.get("age").toString()) > 0);
        assertNull(map.get("sex"));
    }

    class SerializeObject {

        @Expose
        @SerializedName("name")
        private String fullName;
        @Expose
        private int age;
        private Integer sex;
        @Expose
        @SerializedName("money")
        private double treasure;
        @Expose
        @SerializedName("children")
        private List<String> childrens;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public double getTreasure() {
            return treasure;
        }

        public void setTreasure(double treasure) {
            this.treasure = treasure;
        }

        public List<String> getChildrens() {
            return childrens;
        }

        public void setChildrens(List<String> childrens) {
            this.childrens = childrens;
        }
    }
}
