package old.personAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Product {

    @NonNull
    private String id;
    private String name;
}
