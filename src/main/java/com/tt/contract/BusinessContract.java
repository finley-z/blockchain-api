package com.tt.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class BusinessContract extends Contract {
    private static final String BINARY = null;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected BusinessContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BusinessContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SaveEventResponse> getSaveEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Save", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SaveEventResponse> responses = new ArrayList<SaveEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SaveEventResponse typedResponse = new SaveEventResponse();
            typedResponse.firstParty = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.secondParty = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SaveEventResponse> saveEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Save", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SaveEventResponse>() {
            @Override
            public SaveEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SaveEventResponse typedResponse = new SaveEventResponse();
                typedResponse.firstParty = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.secondParty = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> getContracts(String secondParty) {
        Function function = new Function(
                "getContracts", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(secondParty)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> takerecord(String from, String to, BigInteger amount, BigInteger timestamp, String remark) {
        Function function = new Function(
                "takerecord", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(from), 
                new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.generated.Uint256(timestamp), 
                new org.web3j.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> accounts(String param0) {
        final Function function = new Function("accounts", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> getContract() {
        Function function = new Function(
                "getContract", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>> cos(String param0) {
        final Function function = new Function("cos", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>>(
                new Callable<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (String) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<Tuple5<String, String, BigInteger, BigInteger, String>> transactions(BigInteger param0) {
        final Function function = new Function("transactions", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple5<String, String, BigInteger, BigInteger, String>>(
                new Callable<Tuple5<String, String, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple5<String, String, BigInteger, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> transform(String to, BigInteger amount, String remark) {
        Function function = new Function(
                "transform", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(to), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>> contents(String param0, String param1) {
        final Function function = new Function("contents", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.Address(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>>(
                new Callable<Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple8<String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue(), 
                                (String) results.get(7).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> signContract(String firstParty, String secondParty, BigInteger userId, BigInteger shareProfit, BigInteger expireYear, String remark) {
        Function function = new Function(
                "signContract", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(firstParty), 
                new org.web3j.abi.datatypes.Address(secondParty), 
                new org.web3j.abi.datatypes.generated.Uint256(userId), 
                new org.web3j.abi.datatypes.generated.Uint256(shareProfit), 
                new org.web3j.abi.datatypes.generated.Uint256(expireYear), 
                new org.web3j.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<BusinessContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BusinessContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BusinessContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BusinessContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static BusinessContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BusinessContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BusinessContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BusinessContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class SaveEventResponse {
        public String firstParty;

        public String secondParty;
    }
}
