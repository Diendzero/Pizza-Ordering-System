package com.group20.pizza.services.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.group20.pizza.models.Address;
import com.group20.pizza.repositories.AddrRepo;
import com.group20.pizza.models.PizzaUserDetails;

@Service
public class AddrService {
    @Autowired
    private AddrRepo addrRepo;

    public Address newAddr(Address address){
        // check nickname
        if (address.getNickname().length() > 50) throw new IllegalArgumentException("No more than 50 characters in NICKNAME!");

        // check district
        if (address.getDistrict().isEmpty()) throw new IllegalArgumentException("DISTRICT must not be empty!");

        // check local address
        if (address.getLocalAddr().isEmpty()) throw new IllegalArgumentException("LOCAL ADDRESS must not be empty!");
        if (address.getLocalAddr().length() > 200) throw new IllegalArgumentException("No more than 200 characters in LOCAL ADDRESS!");

        // check recipient phone number
        if (address.getRecipientPhone().isEmpty()) throw new IllegalArgumentException("PHONE NUMBER must not be empty!");
        if (address.getRecipientPhone().length() != 11) throw new IllegalArgumentException("PHONE NUMBER should be a 11-digit number!");
        try {
            Long.parseLong(address.getRecipientPhone());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("PHONE NUMBER should be a 11-digit number!");
        }

        // final
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        address.setAccount(phoneNum);
        return addrRepo.save(address);
    }
    
    public List<Address> getAddrList(){
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        if (addrRepo.findByAccount(phoneNum).isEmpty()) throw new NullPointerException("No address available, you can add one!");
        return addrRepo.findByAccount(phoneNum);
    }

    public void deleteSelectedAddr(int id){
        addrRepo.deleteById(id);
    }

    public void selectedAddrChangeDefault(int id){
        String phoneNum = ((PizzaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPhoneNumber();
        Address address = addrRepo.findById(id).orElse(null);
        if (address != null) {
            if (address.isDefault()) address.setNotDefault();
            else {
                List<Address> addressList = addrRepo.findByAccount(phoneNum);
                Address defaultAddress = addressList.stream()
                    .filter(Address::isDefault)
                    .findFirst().orElse(null);
                if (defaultAddress != null) {
                    defaultAddress.setNotDefault();
                    addrRepo.save(defaultAddress);
                }
                address.setDefault();
            }
            addrRepo.save(address);
        }
    }

}
