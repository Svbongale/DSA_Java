package FunctionalProgramming;


// Input Processing: The system shall accept a List<GymMember> as the source of truth.
// The implementation must handle an empty list gracefully (e.g., returning an empty Map rather than throwing an exception).

// Grouping Logic: The implementation shall categorize all members based on their membershipType attribute using the Collectors.groupingBy collector.

// Aggregation: For each membership category, the system shall calculate the arithmetic mean of the monthlyFee attribute.
// This must be achieved using the Collectors.averagingDouble downstream collector.

// Immutability: The input list must remain unmodified. The method should return a new Map<String, Double> representing the final analytics.

// Declarative Style: The implementation shall be entirely declarative.
// It must avoid explicit for, while, or if-else loops to process the collection, relying instead on the Stream pipeline.

// Data Integrity: The resulting map must contain exactly the keys present in the input list, with the calculated average fee as the corresponding value.
// If a member's fee is zero or positive, it must be included in the calculation.


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GymMembership {

    public static void main(String[] args) {

        Operations operations = new Operations();
        operations.initGymMembers();
        List<GymMember> gymMemberList = operations.getAllGymMemberList();
        operations.printAllMemberDetails(gymMemberList);
        operations.printAverageAgeOfMembers(gymMemberList);
        operations.printAllMembersByMembershipType(gymMemberList);

        // Add new workouts for all Gym members
        Workout gymWorkout = new Workout(1, WorkoutType.FULL_BODY);
        operations.doWorkout(gymMemberList.getFirst().memberId, gymWorkout);
        operations.doWorkout(gymMemberList.getLast().memberId, gymWorkout);

        operations.printAllMemberDetails(gymMemberList);

    }
}

enum WorkoutType {
    FULL_BODY,
    LEGS,
    ARMS
}

enum MembershipType {
    BRONZE,
    SILVER,
    GOLD
}

class GymMember {
    int memberId;
    String name;
    int age;
    LocalDate membershipExpDate;
    MembershipType membershipType;
    Workout workoutRecords;

    GymMember(int memberId, String name, int age, LocalDate membershipExpDate, MembershipType membershipType) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.membershipExpDate = membershipExpDate;
        this.membershipType = membershipType;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getMembershipExpDate() {
        return membershipExpDate;
    }

    public void setMembershipExpDate(LocalDate membershipExpDate) {
        this.membershipExpDate = membershipExpDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Workout getWorkoutRecords() {
        return workoutRecords;
    }

    public void setWorkoutRecords(Workout workoutRecords) {
        this.workoutRecords = workoutRecords;
    }
}

class Workout {
    int counter;
    WorkoutType workoutType;

    Workout(int counter, WorkoutType workoutType) {
        this.counter = counter;
        this.workoutType = workoutType;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }
}

class Operations {

    List<GymMember> gymMemberList = new ArrayList<>();

    public void initGymMembers() {
        GymMember gymMember1 = new GymMember(101, "Joe", 30, LocalDate.of(2026, 12, 25), MembershipType.GOLD);
        GymMember gymMember2 = new GymMember(102, "Peter", 32, LocalDate.of(2026, 10, 25), MembershipType.GOLD);
        GymMember gymMember3 = new GymMember(103, "Sanders", 20, LocalDate.of(2026, 10, 25), MembershipType.SILVER);
        GymMember gymMember4 = new GymMember(104, "Robert", 23, LocalDate.of(2026, 10, 25), MembershipType.BRONZE);
        GymMember gymMember5 = new GymMember(105, "Sandy", 17, LocalDate.of(2026, 10, 25), MembershipType.BRONZE);


        // Create a list of GymMembers
        gymMemberList.add(gymMember1);
        gymMemberList.add(gymMember2);
        gymMemberList.add(gymMember3);
        gymMemberList.add(gymMember4);
        gymMemberList.add(gymMember5);
    }

    public List<GymMember> getAllGymMemberList() {
        return gymMemberList;
    }

    public void printAllMemberDetails(List<GymMember> gymMemberList) {
        System.out.println("Printing all member details: ");
        gymMemberList.forEach(gymMember -> {
            String workoutDetails = Optional.ofNullable(gymMember.getWorkoutRecords())
                                    .map(workout -> workout.counter + " for " + workout.workoutType)
                                    .orElse("No records for workout");
            System.out.println("MemberID -> " + gymMember.memberId +  ", Name -> " + gymMember.name + ", Age -> " + gymMember.age + ", Workout Details: " + workoutDetails);
        });
    }

    public void printAverageAgeOfMembers(List<GymMember> gymMemberList) {

        double avgAge = gymMemberList.stream()
                .mapToInt(gymMember -> gymMember.age)
                .average()
                .orElse(0.0);

        System.out.println("Avg Age of members -> " + avgAge + " years");
    }

    public void printAllMembersByMembershipType(List<GymMember> gymMemberList) {
        Map<MembershipType, List<String>> groupedMembers = gymMemberList.stream()
                .collect(
                        Collectors.groupingBy(gymMember -> gymMember.membershipType,
                                Collectors.mapping(gymMember -> gymMember.name,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("Members grouped by MembershipType -> " + groupedMembers.entrySet());
    }

    public void doWorkout(int memberId, Workout workout) {
        gymMemberList.stream()
                .filter(gymMember -> gymMember.getMemberId() == memberId)
                .findFirst()
                .ifPresent(gymMember -> gymMember.setWorkoutRecords(workout));
    }
}




