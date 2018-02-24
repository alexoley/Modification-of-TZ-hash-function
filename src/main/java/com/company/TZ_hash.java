package com.company;

public class TZ_hash {

    public static Matrix getHash(String hash)
    {
        Matrix hashResult = Matrix.identity(2,2);
        for(int i=0; i<hash.length(); i++)
        {
            //if(hash.charAt(i)!='0' && hash.charAt(i)!='1') throw new RuntimeException();
            if(hash.charAt(i)=='0')// && i%2==0
            {
                hashResult=hashResult.mul(Matrix.S0);
                //hashResult=hashResult.mod(16807);
            }
            else if(hash.charAt(i)=='1')// && i%2==1
            {
                hashResult=hashResult.mul(Matrix.S1);
                //hashResult=hashResult.mod(16807);
            }

        }

        return hashResult;

    }
}
