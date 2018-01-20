/**
 * じゃんけん出し手取得 継承クラス
 */
public class RPSPlayer extends RPSPlayerBase {

  /**
   * じゃんけん出し手決定
   */
  public int go() {

    //ランダム
    int ramdam = new java.util.Random().nextInt(3);

    switch (ramdam) {

      case 0:
        //[ ぐー ]
        int rock = RPSType.ROCK.getId();
        //System.out.println(rock);
        return rock;

      case 1:
        //[ ちょき ]
        int scissors = RPSType.SCISSORS.getId();
        //System.out.println(scissors);
        return scissors;

      case 2:
        //[ ぱー ]
        int paper = RPSType.PAPER.getId();
        //System.out.println(paper);
        return paper;

      default:
        return 0;
    }
  }
}
