struct ExpensesCalculator {

    // Returns two numbers that sum to the target value
    func findSum(to target: Int, in input: [Int]) -> [Int]? {
        for index in 0..<input.count {
            // Values before this in the array
            for lowerIndex in 0..<index {
                if input[lowerIndex] + input[index] == target {
                    return [input[lowerIndex], input[index]]
                }
            }

            // Values after this in the array
            for higherIndex in (index + 1)..<input.count {
                if input[higherIndex] + input[index] == target {
                    return [input[index], input[higherIndex]]
                }
            }
        }

        return nil
    }

    // Returns three numbers that sum to the target value
    func findTripletSum(to target: Int, in input: [Int]) -> [Int]? {
        for index in 0..<input.count {
            var data = input
            let value = data.remove(at: index)
            
            if var matchingValues = findSum(to: target - value, in: data) {
                matchingValues.append(value)
                return matchingValues
            }
        }

        return nil
    }

    // Find the product of integer elements in an array
    func product(of input: [Int]?) -> Int {
        guard let input = input else {
            return -1
        }

        return input.reduce(1, { x, y in
            x * y
        })
    }
}