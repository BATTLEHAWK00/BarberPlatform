package cn.battlehawk233.barbershop.service;

import cn.battlehawk233.barbershop.dao.CustomerRepo;
import cn.battlehawk233.barbershop.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CustomerService {
    public static final Logger logger = LoggerFactory.getLogger("UserService");
    private final CustomerRepo customerRepo;

    public List<Customer> fuzzySearchCustomers(String query) {
        List<Customer> result = new ArrayList<>();
        try {
            var id = Integer.parseInt(query);
            result.add(customerRepo.findById(id).get());
        } catch (Exception ignored) {
        }
        result.addAll(customerRepo.findCustomersByUsernameLike(query));
        result.addAll(customerRepo.findCustomersByPhoneLike(query));
        return result
                .stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
