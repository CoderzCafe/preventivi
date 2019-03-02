package writetoexcel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductModel {


    public List<Product> findAll() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Product> result = new ArrayList<>();
            result.add(new Product("p1", "name1", 1000, 1, new Date()));
            result.add(new Product("p2", "name2", 9030, 3, sdf.parse("2019-1-1")));
            result.add(new Product("p3", "name3", 6220, 4, sdf.parse("2018-12-4")));
            result.add(new Product("p4", "name4", 4456, 6, sdf.parse("2018-4-8")));
            result.add(new Product("p5", "name5", 3032, 8, sdf.parse("2019-1-1")));
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
