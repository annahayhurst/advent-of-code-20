//
//  Bag.swift
//  
//
//  Created by Anna Hayhurst on 18/12/2020.
//

struct Bag: Hashable {
    
    /// The colour of this bag
    let colour: String
    
    /// The colours of bags this bag is allowed to contain, paired with how many there should be
    var items: [String: Int] = [:]
}


