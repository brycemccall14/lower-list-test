package hu.sonrisa.interview;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NumberLowerOrEqualServiceTest {

    @Autowired
    private NumberLowerOrEqualService numberLowerOrEqualService;

    private static List<Integer> largeNumberList = new ArrayList<>();
    private static List<Integer> largeThresholdList = new ArrayList<>();
    private static List<Integer> largeExpectedList = new ArrayList<>();

    private static List<Integer> largeRandomNumberList = new ArrayList<>();
    private static List<Integer> largeRandomThresholdList = new ArrayList<>();
    private static List<Integer> largeRandomExpectedList = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        int count = 1000 * 1000;
        for(int i = count; i > 0; i--) {
            largeNumberList.add(i);
            largeThresholdList.add(2);
            largeExpectedList.add(2);
        }
        Random random = new Random();
        for(int i = count; i > 0; i--) {
            largeRandomNumberList.add(i);
            int rnd = random.nextInt(count);
            largeRandomThresholdList.add(rnd);
            largeRandomExpectedList.add(rnd);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test001IllegalNumberArgument() {
        numberLowerOrEqualService.countLowerOrEqualList(null, Arrays.asList(1, 2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test002IllegalThresholdArgument() {
        numberLowerOrEqualService.countLowerOrEqualList(Arrays.asList(1, 2, 3), null);
    }

    @Test
    public void test003SimpleOrderedInput() {
        List<Integer> numbers     = Arrays.asList(1, 2, 3);
        List<Integer> thresholds  = Arrays.asList(1, 3, 2);
        List<Integer> expected    = Arrays.asList(1, 3, 2);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test004SimpleUnorderedInput() {
        List<Integer> numbers     = Arrays.asList(3, 2, 1);
        List<Integer> thresholds  = Arrays.asList(1, 3, 2);
        List<Integer> expected    = Arrays.asList(1, 3, 2);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test010EmptyNumberList() {
        List<Integer> numbers     = Collections.emptyList();
        List<Integer> thresholds  = Arrays.asList(1, 3, 2);
        List<Integer> expected    = Arrays.asList(0, 0, 0);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test011EmptyThresholdList() {
        List<Integer> numbers     = Arrays.asList(1, 2, 3);
        List<Integer> thresholds  = Collections.emptyList();
        List<Integer> expected    = Collections.emptyList();

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test012Boundaries() {
        List<Integer> numbers     = Arrays.asList(1, 2, 4);
        List<Integer> thresholds  = Arrays.asList(1, 2, 5, 3, 4);
        List<Integer> expected    = Arrays.asList(1, 2, 3, 2, 3);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test013InputWithRepetition() {
        List<Integer> numbers     = Arrays.asList(1, 1, 1);
        List<Integer> thresholds  = Arrays.asList(0, 1, 2);
        List<Integer> expected    = Arrays.asList(0, 3, 3);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test014InputWithNegativeNumbers() {
        List<Integer> numbers     = Arrays.asList(-3, -1, 1);
        List<Integer> thresholds  = Arrays.asList(-4, -3, 0);
        List<Integer> expected    = Arrays.asList( 0,  1, 2);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void test015InputWithZero() {
        List<Integer> numbers     = Arrays.asList( 0,  0,  1);
        List<Integer> thresholds  = Arrays.asList(-1, -0,  0);
        List<Integer> expected    = Arrays.asList( 0,  2,  2);

        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(numbers, thresholds);

        assertThat(result).isEqualTo(expected);
    }

    @Test(timeout = 1000)
    public void test100LargeInput() {
        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(largeNumberList, largeThresholdList);

        assertThat(result).isEqualTo(largeExpectedList);
    }

    @Test(timeout = 1000)
    public void test101LargeRandomInput() {
        List<Integer> result = numberLowerOrEqualService.countLowerOrEqualList(largeRandomNumberList, largeRandomThresholdList);

        assertThat(result).isEqualTo(largeRandomExpectedList);
    }

}
