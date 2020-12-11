//
//  PassportChecker.swift
//  
//
//  Created by Anna Hayhurst on 11/12/2020.
//

import Foundation

struct PassportChecker {
    // A fully filled out incoming passport will contain exactly 16 entries
    // (This equates to 8 key:value pairs that were split into array elements)
    private static let maximumLength: Int = 16
    
    // Similarly: If is optional, a valid incoming passport must contain at least 14 entries
    private static let minimumLength: Int = 14
    
    // The set of valid values for the 'ecl' key
    private static let validEyeColours: [String] = ["amb", "blu", "gry", "brn", "grn", "hzl", "oth"]
    
    static func isValid(_ passport: [String]) -> Bool {
        guard passport.count != maximumLength else { return true }
        guard passport.count >= minimumLength else { return false }
        
        // Grab all of the keys from the passport (we don't care about the value)
        // This can be achieved by collecting all the elements with even indices
        let passportKeys: [String] = passport.indices.compactMap {
            if $0 % 2 == 0 {
                // Filter out any blank data as a precaution
                if passport[$0].count > 0 { return passport[$0] }
            }
            return nil
        }
        
        // At this point we know we're dealing with 7 key elements due to the guards
        // Therefore we know if one of those 7 is taken up by the optional cid, we can't
        // possibly have all of the info needed for a valid passport.
        return !passportKeys.contains("cid")
    }
    
    static func isStrictlyValid(_ passport: [String]) -> Bool {
        // First, ensure we have a passport that passes basic validation
        guard isValid(passport) else { return false }
        
        // Then, ensure each key has a valid accompanying value
        for i in stride(from: 0, to: (passport.count - 1), by: 2) {
            guard validateKeyValuePair(key: passport[i], value: passport[i + 1]) else { return false }
        }
        
        return true
    }
    
    private static func validateKeyValuePair(key: String, value: String) -> Bool {
        switch key {
        case "byr":
            return isValidYear(value, min: 1920, max: 2002)
        case "iyr":
            return isValidYear(value, min: 2010, max: 2020)
        case "eyr":
            return isValidYear(value, min: 2020, max: 2030)
        case "hgt":
            return isValidHeight(value)
        case "hcl":
            return isValidHairColour(value)
        case "ecl":
            return validEyeColours.contains(value)
        case "pid":
            return value.count == 9 && Int(value) != nil
        default:
            return true
        }
    }
    
    private static func isValidYear(_ year: String, min: Int, max: Int) -> Bool {
        let year = Int(year) ?? 0
        return year >= min && year <= max
    }
    
    // Not enough validation in here but I've already been looking at today's problem for over 2 hours...
    private static func isValidHeight(_ height: String) -> Bool {
        if height.contains("cm") {
            guard height.count == 5 else { return false }
            
            let centimetres = Int(height.prefix(3)) ?? 0
            return centimetres >= 150 && centimetres <= 193
        }
        
        if height.contains("in") {
            guard height.count == 4 else { return false }
            
            let inches = Int(height.prefix(2)) ?? 0
            return inches >= 59 && inches <= 76
        }
        
        return false
    }
    
    private static func isValidHairColour(_ colour: String) -> Bool {
        guard colour.count == 7 else { return false }
        let hexCharacters: CharacterSet = CharacterSet(charactersIn: "abcdef0123456789")
        
        let colourCode = String(colour.dropFirst())
        return colourCode.unicodeScalars.allSatisfy { hexCharacters.contains($0) }
    }
}
