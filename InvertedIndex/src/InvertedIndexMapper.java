import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;

//Mapper class
public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key1, Text value1, Context context)
        throws IOException, InterruptedException {
        String[] words = value1.toString().split(" ");
        InputSplit file = (InputSplit)context.getInputSplit();
        String fileName = ((FileSplit)file).getPath().getName();
        for(int i = 1; i < words.length; i++) {//Discard line number
            context.write(new Text(words[i]), new Text(fileName));
        }
    }
}