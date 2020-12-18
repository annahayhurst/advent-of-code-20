// Advent of Code
// Day 7 - Handy Haversack

import Foundation

/// Returns a dictionary of colour names paired with their corresponding bag object
func parseData(from data: String) -> [String: Bag] {

    // Each line gives us a bag colour and any corresponding rules it has
    let lines = data.components(separatedBy: "\n").filter { $0.count > 1 }
    var bags: [String: Bag] = [:]
    
    for line in lines {
        // The colour comes before "bags contain", the rules afterwards
        let splitLine = line.components(separatedBy: " bags contain ")
        var bag = Bag(colour: splitLine[0])
        
        // Make sure there are rules to work with
        if splitLine[1] != "no other bags." {
            let ruleData = splitLine[1].components(separatedBy: ", ")
            bag.items = parseRules(from: ruleData)
        }
        
        bags[bag.colour] = bag
    }
    
    return bags
}

/// Returns a dictionary that can represent how many bags of each colour can be contained in the original bag
func parseRules(from data: [String]) -> [String: Int] {
    var rules: [String: Int] = [:]
    
    for item in data {
        let rule = item.components(separatedBy: " ")
        let amount = Int(rule[0]) ?? 0
        let colour = rule[1] + " " + rule[2].replacingOccurrences(of: ".", with: "")
        
        rules[colour] = amount
    }
    
    return rules
}

/// Traverse the bags like a tree to find all the possible bags that will eventually have `colour` within:
/// - The outermost bag is the root of the tree
/// - Any bag we reach with no children (i.e. no rules attached) is a leaf
/// If the outermost bag has any children that can contain the colour, return true.
func contains(bag: Bag?, coloured colour: String, in bags: [String: Bag]) -> Bool {
    guard let bag = bag else { return false }
    
    for (childColour, _) in bag.items {
        // Can the current bag contain this colour? If not, do any of its recursive children?
        if childColour == colour || contains(bag: bags[childColour], coloured: colour, in: bags) {
            return true
        }
    }
    
    return false
}

/// Traverse the bags like a tree to find all the children of the starting bag, and count them up.
func countChildren(of bag: Bag?, in bags: [String: Bag]) -> Int {
    guard let bag = bag else { return 0 }
    
    var counter = 0
    for (childColour, amount) in bag.items {
        // Add the amount of this particular colour
        counter += amount
        // Add the number of children this type of bag has, multiplied by how many there are
        counter += amount * countChildren(of: bags[childColour], in: bags)
    }
    
    return counter
}

// Main
let path = "rules.txt"
let data = try String(contentsOfFile: path, encoding: .utf8)

let bags: [String: Bag] = parseData(from: data)

// Part 1
let bagsThatHoldShinyGold = bags.filter { contains(bag: $0.1, coloured: "shiny gold", in: bags) }
print("Part 1: \(bagsThatHoldShinyGold.count) 🎅") // -> 112

// Part 2
let shinyGoldBag = bags["shiny gold"]!
print("Part 2: \(countChildren(of: shinyGoldBag, in: bags)) 🎅") // -> 6260
