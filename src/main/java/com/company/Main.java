package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    static final int max = (int)Math.pow(2,20);
    static final double percent = 100/Math.pow(2,20);

    public static void main(String[] args) throws IOException, InterruptedException{
        /**
         * Initialize logger
         * Switch off console log with setUseParentHandlers(false)
         * Add no xml format with SimpleFormatter
         */
        Logger logger = Logger.getLogger(Main.class.getName());
        FileHandler fileHandler = new FileHandler("app.log", 10000000, 10000, true);
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
        fileHandler.setFormatter(new SimpleFormatter());
        /**
         * Create HashMap to keep String as Key and Matrix as Value
         */
        /*Map<String, Matrix> hashMap = new HashMap<>();
        String piBin20Digits = new String(PI_generation.calculateBinOnPosition(0).substring(0, 20));
        String piBinDigits = piBin20Digits;
        Matrix newHashMatrix = TZ_hash.getHash(piBin20Digits);
        hashMap.put(piBin20Digits, newHashMatrix);
        System.out.println("Start!");
        for(int i=5; i<3636366; i+=11)
        {
            try(FileWriter writer = new FileWriter("pi_digits", true))
            {
                BufferedWriter fbw = new BufferedWriter(writer);
                fbw.write(piBinDigits);
                fbw.write("\n");
                fbw.close();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
            piBinDigits = PI_generation.calculateBinOnPosition(i);
            for(int j=0; j<44; j++)
            {
                char begin = piBin20Digits.charAt(0);
                char end = piBinDigits.charAt(j);
                piBin20Digits = piBin20Digits.substring(1)+String.valueOf(end);
                String Bin20Digits = new String(piBin20Digits);
                newHashMatrix = newHashMatrix.mulOnTransAndMatrix(begin, end);
                final Matrix H = new Matrix(newHashMatrix.getArray());
                hashMap.entrySet().parallelStream()
                        .filter(h -> h.getValue().equals(H) && !h.getKey().equals(Bin20Digits))
                        .forEach(h -> logger.warning(h.getKey()+"  "+Bin20Digits+"\n"+h.getValue()+"\n"+H));
                hashMap.put(piBin20Digits, newHashMatrix);
            }
            if((i-5) % 11000==0) {
                logger.info(String.valueOf(i));
            }
            TimeUnit.MILLISECONDS.sleep(2);
        }*/

        /**
         * Brute Force of all 20 bit strings
         */
	    Map<String, Matrix> hashMap1 = new HashMap<>();
        for(Map.Entry<String, Matrix> entry2 : hashMap1.entrySet())
        System.out.println(entry2.getKey()+"\n"+entry2.getValue());
        System.out.println("Start!");
        for (int i=0; i<max; i++) {
            String key;
            Matrix value;
            if(Integer.toBinaryString(i).length()<20)
            {
                StringBuilder zeros= new StringBuilder();
                int twenty = 20-Integer.toBinaryString(i).length();
                for(int j=0; j<twenty; j++)
                {
                    zeros.append('0');
                }
                zeros.append(Integer.toBinaryString(i));
                key = zeros.toString();
                value = TZ_hash.getHash(Integer.toBinaryString(i));
                //hashMap1.put(zeros.toString(),TZ_hash.getHash(Integer.toBinaryString(i)));
            }
            else{
                key = Integer.toBinaryString(i);
                value = TZ_hash.getHash(Integer.toBinaryString(i));
                //hashMap1.put(Integer.toBinaryString(i),TZ_hash.getHash(Integer.toBinaryString(i)));
            }
            hashMap1.entrySet().parallelStream()
                    .filter(h -> (h.getValue().equals(value) && !h.getKey().equals(key)) ||
                            (!h.getValue().equals(value) && h.getKey().equals(key)))
                    .forEach(h -> logger.warning(h.getKey()+"  "+key+"\n"+h.getValue()+"\n"+value));
            hashMap1.put(key,value);
            if(i % 16384==0) {
                logger.info(String.valueOf(percent*i+"% --> "+i));
                System.out.print("\r");
                System.out.printf("%.2f%s",percent*i,"%");
            }
            TimeUnit.MILLISECONDS.sleep(3);
        }
        /*Map<String, Matrix> hashMap2 = new HashMap<>(hashMap1);
        for(Map.Entry<String, Matrix> entry1 : hashMap1.entrySet()) {
            for(Map.Entry<String, Matrix> entry2 : hashMap2.entrySet()){
                TimeUnit.MILLISECONDS.sleep(5);
                if(!entry1.getKey().equals(entry2.getKey()) && entry1.getValue().equals(entry2.getValue())){
                    logger.severe(entry1.getKey()+"  "+entry2.getKey()+"\n"+entry1.getValue()+"\n"+entry2.getValue());
                }
            }
        }*/
        logger.info(String.valueOf("100.00% --> "+max));
        System.out.print(100.00+"%");
    }
}
