package hu.sonrisa.interview;

import java.util.List;

public interface NumberLowerOrEqualService {

    /**
     * Returns with the occurrence count of any lower or equal values in the numbers list for each value in
     * the thresholds list. For example:
     *   Numbers: 1, 2, 4, 8
     *   Thresholds: 2, 10
     *   Result: 2, 4:  There are 2 values in the numbers lists which are lesser or equals than 2,
     *                  and all (4) values are lesser than 10.
     *
     * There are no limitations about the values in the numbers list. (Can be zero, negative, etc.)
     *
     * @param numbers incoming numbers
     * @param thresholds incoming thresholds that should be used for count
     *
     * @throws IllegalArgumentException If any of the arguments is null.
     *
     * @return A new list with the lower or equal counts.
     */
    List<Integer> countLowerOrEqualList(List<Integer> numbers, List<Integer> thresholds) throws IllegalArgumentException;
}
