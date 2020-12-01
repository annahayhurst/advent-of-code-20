// Advent of Code 2020 - Day 1
// Report Repair

// Part 1
let calculator: ExpensesCalculator = ExpensesCalculator()
let pair: [Int]? = calculator.findSum(to: 2020, in: data)
let pairResult: Int = calculator.product(of: pair)

print("🎄 Part 1: \(pairResult)") // -> Produces 468051

// Part 2
let triplet: [Int]? = calculator.findTripletSum(to: 2020, in: data)
let tripletResult: Int = calculator.product(of: triplet)

print("🎄 Part 2: \(tripletResult)") // -> Produces 272611658