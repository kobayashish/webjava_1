import java.util.Scanner;

/**
 * じゃんけんゲーム
 */
public class RPSGame {

  public static void main(String[] args) {
    //じゃんけんゲーム
    System.out.println("◇◆じゃんけんゲーム◆◇\n");

    //終了フラグ
    boolean endFlg = false;

    //Loop
    while (endFlg == false) {

      //マニュアル / オート / 終了選択メッセージ表示
      System.out.println("＜ゲーム選択＞\n");
      System.out.println("マニュアル：m");
      System.out.println("オート    ：a");
      System.out.println("終了      ：e\n");

      //入力待ち
      Scanner scan = new Scanner(System.in);
      String input = scan.next();

      switch (input) {

        //[ 入力が 'm' ]
        case "m":
          //マニュアルじゃんけん
          System.out.println("マニュアルじゃんけんを開始します。\n");

          manual();

          break;

        //[ 入力が 'a' ]
        case "a":
          //オートじゃんけん
          System.out.println("オートじゃんけんを開始します。\n");

          auto();

          break;

        //[ 入力が 'e' ]
        case "e":
          //終了
          System.out.println("終了します。\n");
          endFlg = true;
          break;

        //[ 入力が 上記以外 ]
        default:
          //m / a / e の入力促しメッセージ表示
          System.out.println("入力が不適切です。\n");
      }
    }

    //終了メッセージ
    System.out.println("お疲れさまでした。\n");
  }

  /**
   * マニュアル処理
   */
  public static void manual() {

    //結果表示フラグ
    boolean resultFlg = false;

    //勝敗履歴
    int win  = 0;
    int lose = 0;

    //出し手表示用
    String hand;

    for (int i = 1; resultFlg == false; i++) {
      //n回戦目
      System.out.println("[" + i + "回戦目]\n");

      //あいこフラグ
      boolean drawFlg = true;

      //勝敗が決まるまでループ
      while (drawFlg == true) {

        //出し手選択
        System.out.println("出し手を選択してください。\n");
        System.out.println("ぐー  ：1");
        System.out.println("ちょき：2");
        System.out.println("ぱー  ：3");
        System.out.println("終了  ：e\n");

        //入力待ち
        Scanner scan = new Scanner(System.in);
        String select = scan.next();

        //[ 入力が '1'or'2'or'3' ]
        if (select.equals("1") || select.equals("2") || select.equals("3")) {

          //型変換
          int selectHand = Integer.parseInt(select);

          System.out.println("あなたの手");

          hand = hand(selectHand);
          System.out.println(hand);

          //相手の出し手
          RPSPlayerBase player = new RPSPlayer();
          int enemy = player.go();

          System.out.println("相手の手");
          hand = hand(enemy);
          System.out.println(hand + "\n");

          //勝ち負け判定
          System.out.println("＜対戦結果＞");

          if (selectHand == enemy) {

            System.out.println("あいこです。\n");

          } else {

            if ((selectHand + 1) % 3 == enemy % 3) {

              System.out.println("あなたの勝ちです。\n");
              win++;
              drawFlg = false;

            } else {

              System.out.println("あなたの負けです。\n");
              lose++;
              drawFlg = false;
            }

            //勝敗履歴
            System.out.println("[勝敗履歴]");
            System.out.println("勝利数：" + win);
            System.out.println("敗北数：" + lose + "\n");
          }

        } else if (select.equals("e")) { //[ 入力が 'e' ]

          //終了
          System.out.println("終了します。\n");
          resultFlg = true;
          break;

        } else { //[ 入力が 上記以外 ]

          //1 / 2 / 3 / e の入力促しメッセージ表示
          System.out.println("入力が不適切です。\n");
        }
      }
    }

    //勝敗履歴
    System.out.println("[勝敗履歴]");
    System.out.println("勝利数：" + win);
    System.out.println("敗北数：" + lose);

    //総合結果
    if (win == lose) {

      System.out.println("引き分けです。\n");

    } else {

      if (win > lose) {

        System.out.println("勝ち越しです。\n");

      } else {

        System.out.println("負け越しです。\n");
      }
    }
  }

  /**
   * オート処理
   */
  public static void auto() {

    //勝敗履歴
    int winA = 0;
    int winB = 0;

    //出し手表示用
    String hand;

    for (int i = 1; i < 51 ; i++) {
      //n回戦目
      System.out.println("[" + i + "回戦目]\n");

      //あいこフラグ
      boolean drawFlg = true;

      //勝敗が決まるまでループ
      while (drawFlg == true) {

        RPSPlayerBase player = new RPSPlayer();

        //コンピュータAの出し手
        int handA = player.go();
        System.out.println("コンピュータAの手");

        hand = hand(handA);
        System.out.println(hand);

        //コンピュータBの出し手
        int handB = player.go();

        System.out.println("コンピュータBの手");
        hand = hand(handB);
        System.out.println(hand + "\n");


        //勝ち負け判定
        System.out.println("＜対戦結果＞");

        if (handA == handB) {

          System.out.println("あいこです。\n");

        } else {

          if ((handA + 1) % 3 == handB % 3) {

            System.out.println("コンピュータAの勝ちです。\n");
            winA++;
            drawFlg = false;

          } else {

            System.out.println("コンピュータBの勝ちです。\n");
            winB++;
            drawFlg = false;
          }
        }
      }
    }

    //勝敗履歴
    System.out.println("[勝敗履歴]");
    System.out.println("コンピュータA勝利数：" + winA);
    System.out.println("コンピュータB勝利数：" + winB);

    //総合結果
    if (winA == winB) {

      System.out.println("引き分けです。\n");

    } else {

      if (winA > winB) {

        System.out.println("コンピュータAの勝ち越しです。\n");

      } else {

        System.out.println("コンピュータBの勝ち越しです。\n");
      }
    }
  }

  /**
   * 出し手表示
   */
  public static String hand(int hand) {

    switch (hand) {

      case 1:
        //[ ぐー ]
        return "ぐー";

      case 2:
        //[ ちょき ]
        return "ちょき";

      case 3:
        //[ ぱー ]
        return "ぱー";

      default:
        return "エラー";
    }
  }
}
