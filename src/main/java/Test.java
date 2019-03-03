import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        String strs="name=ctl&age=27";
        Map<String ,Object> map = Stream.of(strs.split("&")).map(str -> str.split("=")).collect(Collectors.toMap(s->s[0],s->s[1]));
        System.out.println(map);
    }
}
