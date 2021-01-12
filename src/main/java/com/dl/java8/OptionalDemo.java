package com.dl.java8;

import java.util.Optional;

public class OptionalDemo {
    public Optional<Insurance> nullsafeFindCheapestInsurance(Optional<Person> person,Optional<Car> car){
        if (person.isPresent()&& car.isPresent()){
            return Optional.empty();
//            return Optional.of(findCheapestInsurance(person.get(),car.get()));
        }else {
            return Optional.empty();
        }
    }
}
