package br.com.loto.service.efi;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import br.com.loto.api.dto.efi.EFIPayItemCreateRequet;
import br.com.loto.api.dto.efi.EfiPayPaymentCreateRequet;
import br.com.loto.api.dto.efi.EfiPayResponse;
import br.com.loto.exceptions.EfiLotoException;
import br.com.loto.utils.LotoProp;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class EfiServiceImpl implements IEfiService {

    private final LotoProp lotoProp;

    @Override
    public EfiPayResponse paymentByCreditCard(EfiPayPaymentCreateRequet request) {

        try {

            // Parâmetros do pagamento (valor da transação, dados do cartão)
            Map<String, String> params = new HashMap<>();

            List<Object> items = new ArrayList<>();

            // Itens da compra
            for (EFIPayItemCreateRequet item : request.getItems()) {

                Map<String, Object> jsItem = new HashMap<>();
                jsItem.put("name", item.getName());
                jsItem.put("amount", item.getAmount());  // Quantidade
                jsItem.put("value", item.getValue());  // Valor em centavos (R$50,00)
                items.add(jsItem);
            }

            // Dados do comprador
            Map<String, Object> customer = new HashMap<String, Object>();
            customer.put("name", request.getCreditCard().getCustomer().getName());
            customer.put("cpf", request.getCreditCard().getCustomer().getCpf());
            customer.put("phone_number", request.getCreditCard().getCustomer().getPhoneNumber());
            customer.put("email", request.getCreditCard().getCustomer().getEmail());
            customer.put("birth", request.getCreditCard().getCustomer().getBirth());

            Map<String, Object> billingAddress = new HashMap<String, Object>();
            billingAddress.put("street", request.getCreditCard().getBillingAddress().getStreet());
            billingAddress.put("number", request.getCreditCard().getBillingAddress().getNumber());
            billingAddress.put("neighborhood", request.getCreditCard().getBillingAddress().getNeighborhood());
            billingAddress.put("zipcode", request.getCreditCard().getBillingAddress().getZipcode());
            billingAddress.put("city", request.getCreditCard().getBillingAddress().getCity());
            billingAddress.put("state", request.getCreditCard().getBillingAddress().getState());

            // Dados do cartão
            Map<String, Object> creditCard = new HashMap<String, Object>();
            creditCard.put("installments", 1);
            creditCard.put("billing_address", billingAddress);
            creditCard.put("payment_token", request.getCreditCard().getPaymentToken()); // Você deve gerar o token do cartão
            creditCard.put("customer", customer);

            Map<String, Object> payment = new HashMap<String, Object>();
            payment.put("credit_card", creditCard);

            Map<String, Object> body = new HashMap<String, Object>();
            body.put("items", items);
            body.put("payment", payment);

            EfiPay efi = new EfiPay(lotoProp.getEfiCredential().getOptions());
            return returnResponse(efi.call("createOneStepCharge", params, body));

        } catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new EfiLotoException(e.getErrorDescription(), 4000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EfiLotoException(e.getMessage(), 4000);
        }

    }

    @Override
    public EfiPayResponse refundCreditCard(EfiPayPaymentCreateRequet request) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", "0");

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("amount", 1000);

        try {
            EfiPay efi = new EfiPay(lotoProp.getEfiCredential().getOptions());
            Map<String, Object> response = efi.call("refundCard", params, body);
            System.out.println(response);

            return EfiPayResponse.builder().build();

        } catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public EfiPayResponse updateMetadata(EfiPayPaymentCreateRequet request) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", "0");

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("custom_id", "Product 0001");
        body.put("notification_url", "http://domain.com/notification");

        try {
            EfiPay efi = new EfiPay(lotoProp.getEfiCredential().getOptions());
            Map<String, Object> response = efi.call("updateChargeMetadata", params, body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public EfiPayResponse getCharges(Long chargeId) {

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", chargeId.toString());

        try {
            EfiPay efi = new EfiPay(lotoProp.getEfiCredential().getOptions());
            return returnResponse02(efi.call("detailCharge", params, new HashMap<String, Object>()));

        } catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new EfiLotoException(e.getErrorDescription(), 4000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new EfiLotoException(e.getMessage(), 4000);
        }
    }

    private EfiPayResponse returnResponse(Map<String, Object> response) {

        Integer code = (Integer) response.get("code");

        if (code == 200) {

            Map<String, Object> data = (Map<String, Object>) response.get("data");
            return EfiPayResponse.builder()
                    .status(data.get("status") == null ? null : EFIPaymentStatus.fromDescription((String) data.get("status")))
                    .chargeId(data.get("charge_id") == null ? null : (Integer) data.get("charge_id"))
                    .installments(data.get("installments") == null ? null : (Integer) data.get("installments"))
                    .total(data.get("total") == null ? null : (Integer) data.get("total"))
                    .payment(data.get("payment") == null ? null : EFIPaymentType.fromDescription((String) data.get("payment")))
                    .build();
        }

        return null;
    }

    private EfiPayResponse returnResponse02(Map<String, Object> response) {

        Integer code = (Integer) response.get("code");

        if (code == 200) {

            Map<String, Object> data = (Map<String, Object>) response.get("data");
            return EfiPayResponse.builder()
                    .status(data.get("status") == null ? null : EFIPaymentStatus.fromDescription((String) data.get("status")))
                    .chargeId(data.get("charge_id") == null ? null : (Integer) data.get("charge_id"))
                    .total(data.get("total") == null ? null : (Integer) data.get("total"))
                    .build();
        }

        return null;
    }
}
