package org.tron.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.tron.common.crypto.ECKey;
import org.tron.common.utils.Utils;
import org.tron.core.exception.CipherException;
import org.tron.keystore.Wallet;
import org.tron.protos.Protocol;
import org.tron.walletserver.WalletClient;

public class sum {
  public static void main(String [] s) throws IOException, CipherException {
    File f = new File("/Users/taihaofu/Desktop/test.log");
    FileInputStream inputStream = new FileInputStream("/Users/taihaofu/Desktop/test.log");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    String str = null;
    long sum = 0;
    while((str = bufferedReader.readLine()) != null)
    {
      if(str.equalsIgnoreCase("")){
        continue;
      }
      System.err.println("\nAfter Transaction");
      ECKey key =  new ECKey(Utils.getRandom());
      WalletClient w = new WalletClient(key.getPrivKeyBytes());
      Protocol.Account account = w.queryAccount(w.decodeFromBase58Check(str));
      sum += account.getBalance();
      System.err.println("\n" + str + " : " + account.getBalance());

    }

    System.err.println("\nAfter Transaction sum: "+sum);

    //close
    inputStream.close();
    bufferedReader.close();
  }
}
