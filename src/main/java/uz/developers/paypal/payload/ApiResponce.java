package uz.developers.paypal.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponce {

    private String message;
    private boolean success;

    private Object object;

    public ApiResponce(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
