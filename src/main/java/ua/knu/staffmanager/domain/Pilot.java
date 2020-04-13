package ua.knu.staffmanager.domain;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Builder
public class Pilot extends Staff {

   @NotEmpty(message = "pilot.should.have.at.least.one.permission")
   private List<BoardType> permissions;

   @PositiveOrZero(message = "pilot.work.ours.should.not.be.less.0")
   private Integer workTimeLastDay;

   @PositiveOrZero(message = "pilot.work.ours.should.not.be.less.0")
   private Integer workTimeLastWeek;


}
