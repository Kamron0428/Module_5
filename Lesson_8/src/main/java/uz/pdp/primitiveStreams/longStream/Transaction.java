package uz.pdp.primitiveStreams.longStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class Transaction {
    private String id;
    private long userId;
    long amountIntCents;
    private Status status;



}
