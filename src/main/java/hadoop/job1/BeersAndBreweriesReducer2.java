package hadoop.job1;

import hadoop.Beer;
import hadoop.BeerOrBrewery;
import hadoop.Brewery;
import hadoop.commonjob.CommonMethodReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.List;

public class BeersAndBreweriesReducer2 extends Reducer<IntWritable, BeerOrBrewery,IntWritable,BeerOrBrewery> {
    public void reduce(IntWritable key, Iterable<BeerOrBrewery> values, Context context) throws IOException, InterruptedException {
        CommonMethodReducer methodReducer = new CommonMethodReducer();
        methodReducer.myReduce(values);
        List<Beer> beers = methodReducer.getBeers();
        Brewery brewery = methodReducer.getBrewery();
        if(beers.size()>=5){
            for(Beer beerOrBrewery : beers){
                BeerOrBrewery result = new BeerOrBrewery();
                result.setBrewery(brewery);
                result.setBeer(beerOrBrewery);
                context.write(new IntWritable(beerOrBrewery.getId()), result);
            }
        }
    }

}