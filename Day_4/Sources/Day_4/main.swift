// Advent of Code - Day 4
// Passport Processing

import Foundation

let path = "data.txt"
let data = try String(contentsOfFile: path, encoding: .utf8)

// Split individual passports by double line-breaks
let passportStrings = data.components(separatedBy: "\n\n")

var basicValidationCounter = 0
var strictValidationCounter = 0
for passport in passportStrings {
    // Break everything down into an array of keys and values
    let components = passport.components(separatedBy: CharacterSet([" ", "\n", ":"]))
    
    // Part 1
    if(PassportChecker.isValid(components)) { basicValidationCounter += 1 }
    
    // Part 2
    if(PassportChecker.isStrictlyValid(components)) { strictValidationCounter += 1 }
}

print("Part 1: \(basicValidationCounter) ☃️") // -> 204
print("Part 2: \(strictValidationCounter) ☃️") // -> 179
