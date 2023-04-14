package com.developerstack.medex.db;

import com.developerstack.medex.dto.UserDto;
import com.developerstack.medex.enums.AccountType;

import java.util.ArrayList;

public class Database {
    public static ArrayList<UserDto> userTable = new ArrayList();

    static {
        userTable.add(new UserDto(
                "Samuduni",
                "Wijerathna",
                "hkas.wijerathna@gmail.com",
                "1234",
                AccountType.PATIENT));

        userTable.add(new UserDto(
                "Sasanika",
                "Wijerathna",
                "sasanika@gmail.com",
                "9876",
                AccountType.DOCTOR));
    }
}
