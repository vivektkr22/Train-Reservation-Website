package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.shashi.beans.TrainBean;
import com.shashi.beans.TrainException;
import com.shashi.service.TrainService;
import com.shashi.service.impl.TrainServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/updatetrainschedule")
public class UpdateTrainSchedule extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		try {

			TrainBean train = new TrainBean();
			train.setTr_no(Long.parseLong(req.getParameter("trainno")));
			train.setTr_name(req.getParameter("trainname"));
			train.setFrom_stn(req.getParameter("fromstation"));
			train.setTo_stn(req.getParameter("tostation"));
			train.setSeats(Integer.parseInt(req.getParameter("available")));
			train.setFare(Double.parseDouble(req.getParameter("fare")));

			String message = trainService.updateTrain(train);
			if ("SUCCESS".equalsIgnoreCase(message)) {
				RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Train Updated Successfully!</p1></div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Error in filling the train Detail</p1></div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());
		}

	}

}
