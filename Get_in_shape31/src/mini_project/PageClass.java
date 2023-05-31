package mini_project;

public class PageClass extends User {

	String now_id;
	String now_name;

	User user = new User();



	// 시작페이지 메서드
	public void firstPage() {
		while(true) {

			System.out.println("==============================================================\n");

			System.out.println("< 1.운동하기 / 2.마이페이지 / 3.훈련일지 / 4.쇼핑몰 / 5.로그아웃 >");
			System.out.print(">> ");
			int page = scan.nextInt();
			if(page == 1) {
				Training t =new Training();
				t.now_id = super.now_id;
				t.now_name = super.now_name;

				t.TodayTraining();
				firstPage();
				break;
			}
			else if(page == 2) {
				mypage(); 
				inoutPage();
				break;
			}
			else if(page == 3) {
				openTrainingLog();
				firstPage();
				break;
			}
			else if(page == 4) {
				showShopPage();
				firstPage();
				break;

			} else if(page == 5) {
				signOut();
			}
			else System.out.println("입력이 올바르지 않습니다. 다시 입력하세요");
		}
	}


	// 종료일 이후 페이지 메서드
	public void lastPage() {
		while(true) {

			System.out.println("==============================================================\n");

			System.out.println("< 1.마이페이지 / 2.훈련일지 / 3.쇼핑몰 / 4.로그아웃 >");
			System.out.print(">> ");
			int page = scan.nextInt();
			if(page == 1) {
				mypage(); 
				lastInoutPage();
				break;
			}
			else if(page == 2) {
				openTrainingLog();
				lastPage();
				break;
			}
			else if(page == 3) {
				showShopPage();

				lastPage();
				break;

			} else if(page == 4) {
				signOut();
			}
			else System.out.println("입력이 올바르지 않습니다. 다시 입력하세요");
		}
	}




	// 마이페이지 확인 메소드
	public void mypage() {

		System.out.println("[" + super.now_name+"님의 마이페이지]");
		System.out.print("목표달성률 : ");
		System.out.println(da.getMyPageData(super.now_id).get(0).getGoalRate() + "%");

		System.out.print("포인트 : ");
		System.out.println(da.getMyPageData(super.now_id).get(0).getMember_point() + "점");

		System.out.print("시작일 : ");
		System.out.println(da.getMyPageData(super.now_id).get(0).getStartDate());

		System.out.print("종료일 : ");
		System.out.println(da.getMyPageData(super.now_id).get(0).getEndDate());

	}
	public void inoutPage() {

		while(true) {

			System.out.println("< 1.이전 페이지로 가기 / 2.회원 정보 확인 및 수정 / 3.로그아웃 >");
			/*
			 * 1. 메뉴 입력받기
			 * 2. 입력한 메뉴에 따라 각 메소드 호출(1~4번)
			 * 3. 입력한 메뉴가 5이면 강제 종료 -> SignOut()호출 
			 * 4. 그 외에는 "잘못 입력" 
			 */
			System.out.print(">> ");
			int mnum = scan.nextInt();
			if(mnum == 1) {
				firstPage();
				break;
			} else if(mnum == 2) {
				viewMyInfo();

				while(true) {

					System.out.println("< 1.이전 페이지로 가기 / 2.정보 수정 >");
					int mnum2 = scan.nextInt();
					if(mnum2 == 1) {
						mypage();
						inoutPage();
						break;
					} else if (mnum2 == 2){
						editMyInfo();
						inoutPage();
						break;
					} else {
						System.out.println("입력이 올바르지 않습니다. 다시 입력하세요");
					}
				}
				break;

			} else if(mnum == 3) {
				signOut();
				break;
			} else {
				System.out.println("입력이 올바르지 않습니다. 다시 입력하세요");
			}
		}
	}

	public void lastInoutPage() {

		while(true) {

			System.out.println("<1. 이전 페이지로 가기 / 2. 회원 정보 확인 및 수정 / 3. 로그아웃>");
			/*
			 * 1. 메뉴 입력받기
			 * 2. 입력한 메뉴에 따라 각 메소드 호출(1~4번)
			 * 3. 입력한 메뉴가 5이면 강제 종료 -> SignOut()호출 
			 * 4. 그 외에는 "잘못 입력" 
			 */
			System.out.print(">> ");
			int mnum = scan.nextInt();
			if(mnum == 1) {
				lastPage();
				break;
			} else if(mnum == 2) {
				viewMyInfo();
				while(true) {

					System.out.println("< 1.이전 페이지로 가기 / 2.정보 수정 >");
					int mnum2 = scan.nextInt();
					if(mnum2 == 1) {
						mypage();
						lastInoutPage();
						break;
					} else if (mnum2 == 2){
						editMyInfo();
						lastInoutPage();
						break;
					} else {
						System.out.println("입력이 올바르지 않습니다. 다시 입력하세요");
					}
				}
				break;

			} else if(mnum == 3) {
				signOut();
				break;
			} else {
				System.out.println("입력이 올바르지 않습니다. 다시 입력하세요");
			}
		}
	}

	//쇼핑몰 페이지
	// 쇼핑몰 기본 창
	// 1. 메인페이지를 통해서 들어간다 4 or 5번으로 설정
	// 2. 접속하자마자 "내 포인트 : ? " 보이게 하기
	// 3. 1. 상품보기 2. 구입내역 3. 처음화면
	// 3-1 아래로 1~ n번까지 포인트를 통해 구입할 수 있는 아이템 리스트 정렬
	// 3-1-1 구입하기 >> 원하는 번호 입력
	// 3-2 
	// 4. 해당 아이템 번호 선택시 해당 아이템을 구입하시겠습니까? (Y/N)
	// 5. Y를 선택하면 다시 한번 선택하신 아이템은 ??point가 차감됩니다. 맞으면 Y 틀리면 N을 눌러주세요. 출력 -> n이면 기본창으로 돌아가기.
	// 6. Y이면 구매가 완료되었습니다. 하고 포인트 차감. 아니면 N이면 다시 기본창으로 돌아간다.


	public void showShopPage() {

		System.out.println("Get In Shape 31 포인트몰입니다.");
		System.out.println("안녕하세요 " + super.now_name + "님");

		System.out.println(super.now_name + "님의 등급은 " + da.getMyInfo(super.now_id).get(0).getUserRank() + "입니다.");
		System.out.println(super.now_name + "님의 사용가능한 포인트는 " + da.getMyPageData(super.now_id).get(0).getMember_point() + "점 입니다.");



		System.out.print("-----------------------------------------------");
		System.out.println("");
		for(int i = 0; i <da.getShoppingMallItem().size(); i++) {
			int itemNum = da.getShoppingMallItem().get(i).getItemNum();
			String itemBrand = da.getShoppingMallItem().get(i).getItemBrand();
			String itemName = da.getShoppingMallItem().get(i).getItemName();
			int itemPoint = da.getShoppingMallItem().get(i).getItemPoint();
			System.out.printf("%4d"+"번"+"\t|  %s %s     \t|%7d " + "POINT" + "\n", itemNum, itemBrand, itemName, itemPoint);

			//            System.out.println("-----------------------------------------");
			//            System.out.println("아이템번호 : " + itemNum);
			//            System.out.println("브랜드 : " + itemBrand);
			//            System.out.println("품명 : " + itemName);
			//            System.out.println("포인트 : " + itemPoint);
			//            System.out.println("-----------------------------------------");
		}
		System.out.print("-----------------------------------------------\n");

		selectItem();
	} 

	public void selectItem() {
		System.out.print("구입을 원하는 상품의 번호를 입력하세요 >> ");
		int num = scan.nextInt();
		if(da.getMyPageData(super.now_id).get(0).getMember_point() >= da.getShoppingMallItem().get(num - 1).getItemPoint()) {

			da.buyItem(super.now_id, num);
			System.out.println(num +"번 " + da.getShoppingMallItem().get(num-1).getItemName() + " 구매완료");
			System.out.println("남은 포인트는 " + da.getMyPageData(super.now_id).get(0).getMember_point() + "입니다.");


		} else {
			System.out.println();
			System.out.println("포인트가 부족합니다.");
			System.out.println("운동하기를 통해서 포인트를 모아주세요!");
		}
	}

}