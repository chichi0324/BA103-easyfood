package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cartitem.model.CartItemVO;
import com.cartorder.model.CartOrderVO;
import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.mem.model.MemVO;
import com.pro.model.*;

import com.store.model.StrVO;
import com.store.model.StrService;
import com.tools.tools;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		new tools();

		HttpSession session = req.getSession();
		Vector<CartOrderVO> orderlist = (Vector<CartOrderVO>) session.getAttribute("shoppingcart");

		String action = req.getParameter("action");

		// 刪除購物車中的菜
		if (action.equals("DELETE")) {
			int orderIndex = Integer.parseInt(req.getParameter("orderIndex"));
			int dishIndex = Integer.parseInt(req.getParameter("dishIndex"));
			Integer countDcla_no1Dish = 0;
			Integer countDcla_no2Dish = 0;
			List<Double> Dcla_no1List = new ArrayList<Double>();
			List<Double> Dcla_no2List = new ArrayList<Double>();
			Double disAmount = (double) 0;

			for (int i = 0; i < orderlist.size(); i++) {
				CartOrderVO CartOrder = orderlist.get(i);
				if (i == orderIndex) {
					Vector<CartItemVO> buylist = (Vector<CartItemVO>) CartOrder.getBuylist();
					buylist.removeElementAt(dishIndex);
					CartOrder.setBuylist(buylist);

					// *************************************刪除促銷重算開始*************************************
					// 判斷有無money促銷,並呼叫Cal_Ord_pri()計算總價並回傳總價後乘上促銷折扣, 設定Ord_pri
					if (CartOrder.getPro_cat() == null) {
						CartOrder.setOrd_pri(Cal_Ord_pri((Vector<CartItemVO>) CartOrder.getBuylist()));
						// 若促銷種類等於價格(money),則判斷總價有無大於促銷價格條件(Pro_mon)
					} else if (CartOrder.getPro_cat().equals("money")) {
						// 若總價大於促銷條件(Pro_mon),則打折
						if (Cal_Ord_pri((Vector<CartItemVO>) CartOrder.getBuylist()) > CartOrder.getPro_mon()) {
							CartOrder.setOrd_pri(
									Cal_Ord_pri((Vector<CartItemVO>) CartOrder.getBuylist()) * CartOrder.getPro_dis());
							CartOrder.setProMoneyMeet(true);
							CartOrder.setDisAmount(
									Cal_Ord_pri(buylist) - (Cal_Ord_pri(buylist) * CartOrder.getPro_dis()));
							// 無大於促銷條件則原價
						} else {
							CartOrder.setOrd_pri(Cal_Ord_pri((Vector<CartItemVO>) CartOrder.getBuylist()));
							CartOrder.setProMoneyMeet(false);
						}
					} else if (CartOrder.getPro_cat().equals("class")) {

						for (int k = 0; k < buylist.size(); k++) {
							CartItemVO cartItemVO = buylist.get(k);
							// 若符合種類一(Dcla_no1),
							// 則加入Dcla_no1的List,並用Collections.sort排序價格大小
							if (cartItemVO.getDcla_no().equals(CartOrder.getDcla_no1())) {
								System.out.println("size :" + buylist.size());
								System.out.println("Dcla_no1:" + CartOrder.getDcla_no1());
								for (int l = 0; l < cartItemVO.getQuantity(); l++) {
									Dcla_no1List.add(cartItemVO.getDish_price());
									countDcla_no1Dish++;
									Collections.sort(Dcla_no1List);
								}
								// 若符合種類二(Dcla_no2),
								// 則加入Dcla_no2的List,並用Collections.sort排序價格大小
							} else if (cartItemVO.getDcla_no().equals(CartOrder.getDcla_no2())) {
								System.out.println("size :" + buylist.size());
								System.out.println("Dcla_no2:" + CartOrder.getDcla_no2());
								for (int m = 0; m < cartItemVO.getQuantity(); m++) {
									Dcla_no2List.add(cartItemVO.getDish_price());
									countDcla_no2Dish++;
									Collections.sort(Dcla_no2List);
								}
							}
						}
						// 若種類一的數量大於種類二的數量,則表示有種類二數量的促銷組數
						if (countDcla_no1Dish >= countDcla_no2Dish) {
							// 優惠多少錢(disAmount)等於(=)兩個種類list的最大價格相加乘上(*)1(-)減折扣數.(有幾組就跑幾次迴圈)
							for (int n = 0; n < countDcla_no2Dish; n++) {
								disAmount += ((Dcla_no1List.get((Dcla_no1List.size() - 1 - n))
										+ Dcla_no2List.get((Dcla_no2List.size() - 1 - n)))
										* (1 - CartOrder.getPro_dis()));
							}
							if (countDcla_no2Dish <= 0) {
								CartOrder.setProClassMeet(false);
							}

						} else if (countDcla_no1Dish <= countDcla_no2Dish) {
							for (int n = 0; n < countDcla_no1Dish; n++) {
								disAmount += ((Dcla_no1List.get((Dcla_no1List.size() - 1 - n))
										+ Dcla_no2List.get((Dcla_no2List.size() - 1 - n)))
										* (1 - CartOrder.getPro_dis()));
								System.out.println("Dcla_no1List.get(" + n + ") :" + Dcla_no1List.get(n));
								System.out.println("Dcla_no2List.get(" + n + ") :" + Dcla_no2List.get(n));
							}
							if (countDcla_no1Dish <= 0) {
								CartOrder.setProClassMeet(false);
							}
						}
						System.out.println("disAmount :" + disAmount);
						System.out.println("total :" + Cal_Ord_pri(buylist));
						// 總價減掉折扣(disAmount)
						CartOrder.setDisAmount(disAmount);
						CartOrder.setOrd_pri(Cal_Ord_pri(buylist) - disAmount);
					}
					// *************************************刪除促銷重算結束*************************************

					orderlist.setElementAt(CartOrder, i);
					if (buylist.isEmpty() || buylist.size() == 0) {
						orderlist.remove(i);
					}
				}
			}
		}
		// 新增菜至購物車中
		else if (action.equals("ADD")) {

			boolean dishMatch = false;
			boolean strMatch = false;

			// 取得來自search_store_menu的Parameter
			// String mem_no = "MEM_0001";
			// String ord_type = (String) req.getParameter("ord_type");
			String dish_no = (String) req.getParameter("dish_no");

			DishService Dishsvc = new DishService();
			DishVO aDish = Dishsvc.getOneDish(dish_no);
			String str_no = aDish.getStr_no();
			StrService StrSvc = new StrService();
			StrVO aStore = StrSvc.getOneStr(str_no);
			String str_name = aStore.getStr_name();
			String str_ship = aStore.getStr_ship();

			// 取得後來新增的菜用CartItemVO包裝
			CartItemVO aCartItem = new CartItemVO();
			aCartItem.setDish_no(dish_no);
			aCartItem.setQuantity(Integer.parseInt(req.getParameter("quantity")));
			aCartItem.setDish_price((Double) aDish.getDish_price());
			aCartItem.setDish_name((String) aDish.getDish_name());
			aCartItem.setDcla_no(aDish.getDcla_no());

			// 新增訂單,用CartorderVO包裝(訂單)
			CartOrderVO aCartOrder = new CartOrderVO();
			// aCartOrder.setMem_no(mem_no);
			aCartOrder.setStr_no((String) aDish.getStr_no());
			aCartOrder.setStr_name(str_name);
			aCartOrder.setStr_ship(str_ship);
			// if (!str_ship.equals("TRUE")) {
			// aCartOrder.setOrd_type("自取");
			// }

			Vector<CartItemVO> buylist = new Vector<CartItemVO>();
			buylist.add(aCartItem);
			aCartOrder.setBuylist(buylist);

			// 看看有無促銷
			ProService proSvc = new ProService();
			// 先列出此店全部促銷
			List<ProVO> strProList = proSvc.getStrPro(str_no);

			if (!strProList.isEmpty()) {
				// 若此店有促銷,則跑迴圈找出當前的促銷
				for (int i = 0; i < strProList.size(); i++) {
					ProVO proVO = strProList.get(i);
					if (tools.todayTime().getTime() > proVO.getPro_str().getTime()
							&& tools.todayTime().getTime() < proVO.getPro_end().getTime()) {
						if (proVO.getPro_cat().equals("money")) {
							aCartOrder.setPro_cat("money");
							aCartOrder.setPro_mon(proVO.getPro_mon());
							aCartOrder.setPro_dis(proVO.getPro_dis());

						} else if (proVO.getPro_cat().equals("class")) {
							aCartOrder.setPro_cat("class");
							aCartOrder.setDcla_no1(proVO.getDcla_no1());
							aCartOrder.setDcla_no2(proVO.getDcla_no2());
							aCartOrder.setPro_dis(proVO.getPro_dis());
							System.out.println(aCartOrder.getPro_cat());
							System.out.println(aCartOrder.getPro_dis());
							System.out.println(aCartOrder.getDcla_no1());
							System.out.println(aCartOrder.getDcla_no2());
							System.out.println("************************");

						}
					}
				}
			}

			// *************************************算促銷開始*************************************
			// 判斷有無money促銷,並呼叫Cal_Ord_pri()計算總價並回傳總價後乘上促銷折扣, 設定Ord_pri
			if (aCartOrder.getPro_cat() == null) {
				aCartOrder.setOrd_pri(Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist()));
				// 若促銷種類等於價格(money),則判斷總價有無大於促銷價格條件(Pro_mon)
			} else if (aCartOrder.getPro_cat().equals("money")) {
				// 若總價大於促銷條件(Pro_mon),則打折
				if (Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist()) > aCartOrder.getPro_mon()) {
					aCartOrder.setOrd_pri(
							Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist()) * aCartOrder.getPro_dis());
					aCartOrder.setProMoneyMeet(true);
					// 為在購物車顯示省多少用
					aCartOrder.setDisAmount(Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist())
							- (Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist()) * aCartOrder.getPro_dis()));
					// 無大於促銷條件則原價
				} else {
					aCartOrder.setOrd_pri(Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist()));
				}
			} else if (aCartOrder.getPro_cat().equals("class")) {
				aCartOrder.setOrd_pri(Cal_Ord_pri((Vector<CartItemVO>) aCartOrder.getBuylist()));
			}
			// *************************************算促銷結束*************************************

			// 新增第一個Cartorder至購物車時
			if (orderlist == null) {
				orderlist = new Vector<CartOrderVO>();

				// 將aCartorder(新訂單)擺進orderlist(訂單清單)
				orderlist.add(aCartOrder);
				// 非第一次加入購物車
			} else {
				for (int i = 0; i < orderlist.size(); i++) {
					CartOrderVO CartOrder = orderlist.get(i);
					// 假若新增aCartorder(新訂單)的str_no(店家)和購物車裡的Cartorder(已有訂單)的str_no(店家)一樣時
					if (CartOrder.getStr_no().equals(aCartOrder.getStr_no())) {

						Vector<CartItemVO> oldbuylist = (Vector<CartItemVO>) CartOrder.getBuylist();
						for (int j = 0; j < oldbuylist.size(); j++) {

							CartItemVO CartItem = oldbuylist.get(j);

							// 假若新增的CartItem(菜)和購物車的CartItem(菜)一樣時
							if (CartItem.getDish_no().equals(aCartItem.getDish_no())) {
								CartItem.setQuantity(CartItem.getQuantity() + aCartItem.getQuantity());
								oldbuylist.setElementAt(CartItem, j);
								dishMatch = true;

							} // end of if CartItem(菜) matches
						} // end of for

						// 假若新增的菜和購物車的菜不一樣時
						if (!dishMatch) {
							oldbuylist.add(aCartItem);
						}
						CartOrder.setBuylist(oldbuylist);
						strMatch = true;
					} // end of if str_no(店家) matches

					// *************************************算促銷開始*************************************
					// 判斷有無money促銷,並呼叫Cal_Ord_pri()計算總價並回傳總價後乘上促銷折扣,設定Ord_pri
					Integer countDcla_no1Dish = 0;
					Integer countDcla_no2Dish = 0;
					List<Double> Dcla_no1List = new ArrayList<Double>();
					List<Double> Dcla_no2List = new ArrayList<Double>();
					Double disAmount = (double) 0;

					Vector<CartItemVO> oldbuylist = (Vector<CartItemVO>) CartOrder.getBuylist();

					if (CartOrder.getPro_cat() == null) {
						CartOrder.setOrd_pri(Cal_Ord_pri(oldbuylist));

						// 若促銷種類等於價格(money),則判斷總價有無大於促銷價格條件(Pro_mon)
					} else if (CartOrder.getPro_cat().equals("money")) {

						if (Cal_Ord_pri(oldbuylist) > CartOrder.getPro_mon()) {
							CartOrder.setOrd_pri(Cal_Ord_pri(oldbuylist) * CartOrder.getPro_dis());
							CartOrder.setProMoneyMeet(true);
							// 為在購物車顯示省多少用
							CartOrder.setDisAmount(
									Cal_Ord_pri(oldbuylist) - (Cal_Ord_pri(oldbuylist) * CartOrder.getPro_dis()));
						} else {
							CartOrder.setOrd_pri(Cal_Ord_pri(oldbuylist)); // 沒加此行也可以跑,//
																			// 有錯在刪掉
							CartOrder.setProMoneyMeet(false);

						}
						// 若促銷種類等於種類(class),則判斷銷種類條件(Dcla_no)
					} else if (CartOrder.getPro_cat().equals("class")) {

						for (int k = 0; k < oldbuylist.size(); k++) {

							CartItemVO cartItemVO = oldbuylist.get(k);
							// 若符合種類一(Dcla_no1),
							// 則加入Dcla_no1的List,並用Collections.sort排序價格大小
							if (cartItemVO.getDcla_no().equals(CartOrder.getDcla_no1())) {

								for (int l = 0; l < cartItemVO.getQuantity(); l++) {
									Dcla_no1List.add(cartItemVO.getDish_price());
									countDcla_no1Dish++;
									Collections.sort(Dcla_no1List);
								}
								// 若符合種類二(Dcla_no2),
								// 則加入Dcla_no2的List,並用Collections.sort排序價格大小
							} else if (cartItemVO.getDcla_no().equals(CartOrder.getDcla_no2())) {
								System.out.println("size :" + oldbuylist.size());
								System.out.println("Dcla_no2:" + CartOrder.getDcla_no2());
								for (int m = 0; m < cartItemVO.getQuantity(); m++) {
									Dcla_no2List.add(cartItemVO.getDish_price());
									countDcla_no2Dish++;
									Collections.sort(Dcla_no2List);
								}
							}
						}
						// 若種類一的數量大於種類二的數量,則表示有種類二數量的促銷組數
						if (countDcla_no1Dish >= countDcla_no2Dish) {
							// 優惠多少錢(disAmount)等於(=)兩個種類list的最大價格相加乘上(*)1(-)減折扣數.(有幾組就跑幾次迴圈)
							for (int n = 0; n < countDcla_no2Dish; n++) {
								disAmount += ((Dcla_no1List.get((Dcla_no1List.size() - 1 - n))
										+ Dcla_no2List.get((Dcla_no2List.size() - 1 - n)))
										* (1 - CartOrder.getPro_dis()));

							}
							if (countDcla_no2Dish >= 1) {
								CartOrder.setProClassMeet(true);
							}
							// 若種類二的數量大於種類一的數量,則表示有種類一數量的促銷組數
						} else if (countDcla_no1Dish <= countDcla_no2Dish) {
							// 優惠多少錢(disAmount)等於(=)兩個種類list的最大價格相加乘上(*)1(-)減折扣數.(有幾組就跑幾次迴圈)
							for (int n = 0; n < countDcla_no1Dish; n++) {
								disAmount += ((Dcla_no1List.get((Dcla_no1List.size() - 1 - n))
										+ Dcla_no2List.get((Dcla_no2List.size() - 1 - n)))
										* (1 - CartOrder.getPro_dis()));
								System.out.println("Dcla_no1List.get(" + n + ") :" + Dcla_no1List.get(n));
								System.out.println("Dcla_no2List.get(" + n + ") :" + Dcla_no2List.get(n));
							}
							if (countDcla_no1Dish >= 1) {
								CartOrder.setProClassMeet(true);
							}
						}
						System.out.println("disAmount :" + disAmount);
						System.out.println("total :" + Cal_Ord_pri(oldbuylist));
						// 為在購物車顯示省多少用
						CartOrder.setDisAmount(disAmount);
						// 總價減掉折扣(disAmount)
						CartOrder.setOrd_pri(Cal_Ord_pri(oldbuylist) - disAmount);

					}
					// *************************************算促銷結束*************************************
					orderlist.setElementAt(CartOrder, i);
					System.out.println("#############################");
					System.out.println("countDcla_no1Dish :" + countDcla_no1Dish);
					for (Double s : Dcla_no1List) {
						System.out.println(s);
					}

					System.out.println("countDcla_no2Dish :" + countDcla_no2Dish);
					for (Double s : Dcla_no2List) {
						System.out.println(s);
					}

				}
				// 假若新增的Cartorder(訂單)的str_no(店家)和購物車的的Cartorder(訂單)的str_no(店家)不一樣時
				if (!strMatch) {
					orderlist.add(aCartOrder);
				}
			}
		}

		// 傳值用的中間站 shoppingCart到CartServlet到payment_address
		if (action.equals("beforeCHECKOUT")) { // shoppingCart來的請求

			int CheckOutOrderIndex = Integer.parseInt(req.getParameter("CheckOutOrderIndex"));
			System.out.println("beforeCHECKOUT_CheckOutOrderIndex:" + CheckOutOrderIndex);
			CartOrderVO CartOrder = orderlist.get(CheckOutOrderIndex);

			String ord_type = req.getParameter("ord_type");
			if (ord_type == null || ord_type.equals("自取")) {
				ord_type = "自取";
				CartOrder.setOrd_type(ord_type);
			} else if (ord_type.equals("外送")) {
				ord_type = "外送";
				CartOrder.setOrd_type("外送");
			}
			orderlist.setElementAt(CartOrder, CheckOutOrderIndex);
			session.setAttribute("ord_type", ord_type);
			System.out.println("beforeCHECKOUT_ord_type:" + ord_type);

			session.setAttribute("CheckOutOrderIndex", CheckOutOrderIndex);

			if (ord_type.equals("外送")) {
				String url = "/easyfood/front-end/class/shoppingCart/payment_address.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			} else if (ord_type.equals("自取")) {
				action = "CHECKOUT";
			}

		}

		if (action.equals("CHECKOUT")) { // 來自payment_address

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			// MemVO memVO = (MemVO) session.getAttribute("memVO");
			// if(memVO == null){
			// String url =
			// "/easyfood/front-end/class/member/Ord.do";com.filter.FilterLogin
			// RequestDispatcher rd = req.getRequestDispatcher(url);
			// rd.forward(req, res);
			// }
			System.out.println("#CheckOutOrderIndex: " + session.getAttribute("CheckOutOrderIndex"));
			int CheckOutOrderIndex = (Integer) session.getAttribute("CheckOutOrderIndex");
			// String add_adds = req.getParameter("add_adds");
			// System.out.println("CheckOutOrderIndex : "+CheckOutOrderIndex);
			CartOrderVO CartOrder = orderlist.get(CheckOutOrderIndex);
			String ord_type = (String) session.getAttribute("ord_type");
			String add_addsFormFav = req.getParameter("add_addsFormFav");
			String add_adds = req.getParameter("add_adds");
			if (ord_type.equals("外送")) {
				System.out.println("進地址空值判斷");
				if ((add_adds == null || (add_adds.trim()).length() == 0) && add_addsFormFav == null) {

					errorMsgs.add("請輸入外送地址");
				}
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/easyfood/front-end/class/member/payment_address.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			System.out.println("add_adds:" + add_adds);
			// System.out.println("add_adds.length:" + add_adds.length());
			System.out.println("add_addsFormFav:" + add_addsFormFav);
			System.out.println("ord_type:" + ord_type);

			if (ord_type.equals("外送")) {
				if ((add_adds.trim()).length() != 0) {
					System.out.println("跑add_adds");
					CartOrder.setAdd_adds(add_adds);
				} else {
					System.out.println("跑add_addsFormFav");
					CartOrder.setAdd_adds(add_addsFormFav);
				}
				CartOrder.setOrd_type("外送");
			}
			session.setAttribute("action", "addOrd");
			session.setAttribute("CartOrder", CartOrder);
			session.setAttribute("navbar_select", "member");
			String url = "/easyfood/front-end/class/member/Ord.do";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			orderlist.remove(CheckOutOrderIndex);

			// } catch (Exception e) {
			// errorMsgs.add(e.getMessage());
			// RequestDispatcher failureView =
			// req.getRequestDispatcher("/shoppingCart.jsp");
			// failureView.forward(req, res);
			// }
		}

		session.setAttribute("shoppingcart", orderlist);

		if (action.equals("DELETE")) {

			String url = "/easyfood/front-end/class/shoppingCart/shoppingCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		} else if (action.equals("ADD")) {

			String url = "/easyfood/front-end/class/search/enter_storeMenu.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}
	}

	// 計算總價的method
	private Double Cal_Ord_pri(Vector<CartItemVO> buylist) {
		Double orderTotalPrice = (double) 0;
		for (int i = 0; i < buylist.size(); i++) {
			CartItemVO CartItem = buylist.get(i);
			Double price = CartItem.getDish_price();
			int quantity = CartItem.getQuantity();
			orderTotalPrice += (price * quantity);

		}
		return orderTotalPrice;
	}

}
