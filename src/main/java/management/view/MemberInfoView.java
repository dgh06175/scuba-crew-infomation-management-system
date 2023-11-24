package management.view;

import management.model.ClubMemberInformation;
import java.util.List;

public class MemberInfoView {
    public void displayMembers(List<ClubMemberInformation> members) {
        for (ClubMemberInformation member : members) {
            System.out.println("Student ID: " + member.getStudentId() + ", Name: " + member.getName() + ", Department: " + member.getDepartment());
        }
    }
}
