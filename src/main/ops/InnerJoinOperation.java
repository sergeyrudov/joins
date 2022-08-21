package ops;

import data.DataRow;
import data.JoinedDataRow;
import java.util.ArrayList;
import java.util.Collection;

/**
 * --Short description--
 *  1. get key from right table, and compare it with key in left table
 *  2. if keys equals, return joined data in format:
 *  3. key from left table, value1 from left table, value2 from right table
 *  --------------------------------------------------------------
 *  4. if keys are not equal, returned void
 */
public class InnerJoinOperation<K, V1, V2> implements JoinOperation<DataRow<K, V1>, DataRow<K, V2>, JoinedDataRow<K, V1, V2>> {

    @Override
    public Collection<JoinedDataRow<K, V1, V2>> join(Collection<DataRow<K, V1>> leftCollection,
                                                     Collection<DataRow<K, V2>> rightCollection) {

        if (leftCollection == null || rightCollection == null) {
            throw new IllegalArgumentException("Collection cannot be null");
        }

        Collection<JoinedDataRow<K, V1, V2>> result = new ArrayList<>();

        leftCollection.forEach(value1 -> {
            rightCollection.stream()
                    .filter(value2 -> value1.getKey() == value2.getKey())
                    .forEach(value2 ->
                            result.add(new JoinedDataRow<>(value1.getKey(), value1.getValue(), value2.getValue()))
                    );
        });
        return result;
    }
}