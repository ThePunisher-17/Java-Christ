import java.util.*;

public class task2_program_1 {
    static int[] array = { 1, 2, 3, 3, 5, 5, 6, 7, 7, 9, 9, 9, 10 };

    static void initaite_arr(int k) 
    {

        Dictionary<Integer, Integer> dict = new Hashtable<Integer, Integer>();

        for (int i = 0; i < array.length; i++) 
        {
            dict.put(array[i], 0);
        }
        // System.out.println(dict);

        Enumeration<Integer> keys = dict.keys();

        if(!dict.isEmpty())
        {
            while (keys.hasMoreElements())
            {
                Integer key = keys.nextElement();
                for (int i = 0; i < array.length; i++)
                    {
                        if (array[i] == key)
                        {
                            dict.put(key, dict.get(key) + 1);
                        }
                    }
            }
            // System.out.println(dict);
        }
        
        Enumeration<Integer> keys2 = dict.keys();
        int[] keys_array = new int[dict.size()];
        int [] values_array = new int[dict.size()];
        int i = 0;

        while (keys2.hasMoreElements())
        {
            Integer key = keys2.nextElement();
            keys_array[i] = key;
            values_array[i] = dict.get(key);
            // System.out.println(keys_array[i] + " " + values_array[i]);
            i++;
        }

        for (int j = 0; j < keys_array.length - 1; j++)
        {
            for (int l = j + 1; l < keys_array.length; l++)
            {
                if (values_array[j] < values_array[l])
                {
                    int temp = values_array[j];
                    values_array[j] = values_array[l];
                    values_array[l] = temp;
                    int temp2 = keys_array[j];
                    keys_array[j] = keys_array[l];
                    keys_array[l] = temp2;
                }
            }
        }
        
        System.out.println("top " + k + " frequent numbers are: ");
        for (int j = 0; j < k; j++)
        {
            System.out.println(keys_array[j]);
        }
    }

    public static void main(String[] args) 
    {
        task2_program_1.initaite_arr(2);
    }
}
