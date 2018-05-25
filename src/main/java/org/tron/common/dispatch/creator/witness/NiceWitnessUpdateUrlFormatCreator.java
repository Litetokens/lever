package org.tron.common.dispatch.creator.witness;

import com.google.protobuf.ByteString;
import java.util.concurrent.atomic.AtomicLong;
import org.tron.common.crypto.ECKey;
import org.tron.common.dispatch.AbstractTransactionCreator;
import org.tron.common.dispatch.BadCaseTransactionCreator;
import org.tron.common.utils.ByteArray;
import org.tron.protos.Contract;
import org.tron.protos.Protocol;

public class NiceWitnessUpdateUrlFormatCreator extends AbstractTransactionCreator implements BadCaseTransactionCreator {
  private AtomicLong serialNum = new AtomicLong(0);

  @Override
  protected Protocol.Transaction create() {
    Contract.WitnessUpdateContract contract = Contract.WitnessUpdateContract.newBuilder()
        .setOwnerAddress(witnessAddress)
        .setUpdateUrl(ByteString.copyFrom(ByteArray.fromString("http://Mercury.org")))
        .build();
    Protocol.Transaction transaction = client.getRpcCli().updateWitness(contract);
    transaction = client.signTransaction(transaction, ECKey.fromPrivate(ByteArray.fromHexString(witnessPrivateKey)));
    return transaction;
  }
}