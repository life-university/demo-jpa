package com.example.jpa_basic.equals;

import com.example.jpa_basic.hellojpa.Member;

public class ExDiff {

    public static void main(String[] args) {
        Member member1 = new Member();
        member1.setId(1L);
        member1.setName("Member1");

        Member member2 = new Member();
        member2.setId(1L);
        member2.setName("Member1");

        Member member3 = member2;

        System.out.println("member1 = " + member1);
        System.out.println("member1 address = " + System.identityHashCode(member1));
        System.out.println("member2 = " + member2);
        System.out.println("member2 address = " + System.identityHashCode(member2));
        System.out.println("member3 = " + member2);
        System.out.println("member3 address = " + System.identityHashCode(member2));
        // 1. equality check using equals method
        if (member1.equals(member2)) {
            System.out.println("equals : member1 and member2 are equal.");
        } else {
            System.out.println("equals : member1 and member2 are not equal.");
        }
        // 2. identity check using '==' operator
        if (member1 == member2) {
            System.out.println("== : member1 and member2 are equal.");
        } else {
            System.out.println("== : member1 and member2 are not equal.");
        }
        // 3. member2.equals(member3) check
        if (member2.equals(member3)) {
            System.out.println("member2.equals(member3) : member2 and member3 are equal.");
        } else {
            System.out.println("member2.equals(member3) : member2 and member3 are not equal.");
        }
        // 4. member2 == member3 check
        if (member2 == member3) {
            System.out.println("member2 == member3 : member2 and member3 are equal.");
        } else {
            System.out.println("member2 == member3 : member2 and member3 are not equal.");
        }
    }
}
