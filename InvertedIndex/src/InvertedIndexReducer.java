import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

//Reducer class
public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key3, Iterable<Text> value3, Context context)
        throws IOException, InterruptedException {
        StringBuilder dict = new StringBuilder();
        List<String> dicts = new ArrayList<>();
        for(Text value:value3) {
            //Data deduplication
            if(!dicts.contains(value.toString())) {
                dicts.add(value.toString());
                dict.append(value.toString());
                dict.append(",");
            }
        }
        context.write(key3, new Text(dict.toString()));
    }
}
